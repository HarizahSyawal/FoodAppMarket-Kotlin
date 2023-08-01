<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class MidtransController extends Controller
{
    public function callback(Request $request)
    {
        //set midtrans config
        Config::$serverKey = config('services.midtrans.serverKey');
        Config::$isProduction = config('services.midtrans.isProduction');
        Config::$isSanitized = config('services.midtrans.isSanitized');
        Config::$is3ds = config('services.midtrans.is3ds');

        //create instance midtrans notification
        $notification = new Notification();
        //Assign variable
        $status = $notification->transaction_status;
        $type = $notification->payment_type;
        $fraud = $notification->fraud_status;
        $order_id = $notification->order_id;
        //Find transaction based on ID
        $transaction = Transaction::findOrFail($order_id);
        //Handle notification midtrans status
        if($status == 'capture')
        {
            if($type == 'credit_card'){
                $transaction->status = 'PENDING';
            }
            else{
                $transaction->status = 'SUCCESS';
            }
        }
        else if($status == 'settlement'){
            $transaction->status = 'SUCCESS';
        }
        else if($status == 'pending'){
            $transaction->status = 'PENDING';
        }
        else if($status == 'deny'){
            $transaction->status = 'CANCELLED';
        }
        else if($status == 'expire'){
            $transaction->status = 'CANCELLED';
        }
        else if($status == 'cancel'){
            $transaction->status = 'CANCELLED';
        }
        //Save Transaction
        $transaction->save();
    }

    public function success(){
        return view('midtrans.success');
    }

    public function unfinish(){
        return view('midtrans.unfinish');
    }

    public function error(){
        return view(midtrans.error);
    }
}
