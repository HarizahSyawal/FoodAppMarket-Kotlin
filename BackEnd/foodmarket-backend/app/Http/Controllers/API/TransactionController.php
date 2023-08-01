<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class TransactionController extends Controller
{
    public function all(Request $request){

        $id = $request->input('id');
        $limit = $request->input('limit', 6);
        $food_id = $request->input('food_id');
        $status = $request->input('status');


        if($id)
        {
            $transaction = Transaction::with(['food','user'])->find($id);

            if($transaction){
                return ResponseFormatter::success(
                    $transaction,
                    'Transaction data successfull added'
                );
            }          else{
                return ResponseFormatter::error(
                    null,
                    'Transaction data not found',
                    404
                );
            }
        }

        $transaction = Transaction::with(['food',user])
        ->where('user_id', Auth::user()->id);

        if($food_id){
            $transaction->where('food_id', $food_id);
        }

        if($status){
            $transaction->where('status', $status);
        }

        return ResponseFormatter::success(
            $transaction->paginate($limit),
            'Data list of transaction successfully added'
        );
    }
    public function update(Request $request){
        $transaction = Transaction::findOrFail($id);

        $transaction ->update($request->all());

        return ResponseFormatter::success(
            $food->paginate($limit),
            'Transaction successfully updated'
        );
    }
    public function checkout(Request $request){
        $request -> validate([
            'food_id'=> 'required|exists:food,id',
            'user_id'=> 'required|exists:users,id',
            'quantity'=> 'required',
            'total'=> 'required',
            'status'=> 'required',
        ]);

        $transaction = Transacation::create([
            'food_id'=> $request->food_id,
            'user_id'=> $request->user_id,
            'quantity'=> $request->quantity,
            'total'=> $request->total,
            'status'=> $request->status,
            'payment_url'=>''
        ]);

        //Config MidTrans
        Config::$serverKey = config('services.midtrans.serverKey');
        Config::$isProduction = config('services.midtrans.isProduction');
        Config::$isSanitized = config('services.midtrans.isSanitized');
        Config::$is3ds = config('services.midtrans.is3ds');

        //Calling transaction that was created earlier
        $transaction = Transaction::with(['food','user'])->find($transaction->id);

        //Create MidTrans transaction

        $midtrans = [
            'transaction_details' => [
                'order_id' => $transaction->id,
                'gross_amount' => (int) $transaction->total,
            ],
            'customer_details'=>[
                'first_name' => $transaction->user->name,
                'email' => $transaction->user->email,
            ],
            'enabled_payments' => ['gopay', 'bank_transfer'],
            'vtweb' => [],
        ];

        //Calling Midtrans
        try {
            $paymentUrl = Snap::createTransaction($midtrans)->redirect_url;
            $transaction->payment_url = $paymentUrl;
            $transaction->save();

            return ResponseFormatter::success($transaction, 'Transaction Successfull');
        } catch (\Throwable $e) {
            return ResponseFormatter::error($e->getMessage(), 'Transaction Failed');
        }
    }
}
