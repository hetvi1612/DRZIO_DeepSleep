package drzio.insomnia.treatment.bedtime.yoga.sleep.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class DynamicBillingCheck {
    private static final String TAG = "BillingCheck";
    IabHelper mHelper;
    String mDelaroySku = "";
    private boolean mAutoRenewEnabled;
    private boolean mSubscribedToDelaroy;
    TinyDB tinyDB;
    Context mContext;
    static  String ITEM_SKU = "";
    String SKU_DELAROY_MONTHLY="";
    String SKU_DELAROY_SIXMONTH="";
    public void onCreate(Context context) {
        String base64EncodedPublicKey = context.getString(R.string.base64key);
        tinyDB = new TinyDB(context);
        mHelper = new IabHelper(context, base64EncodedPublicKey);
        mContext = context;

        ITEM_SKU = tinyDB.getString(Constants.ITEM_SKU1);

        SKU_DELAROY_MONTHLY=tinyDB.getString(Constants.SKU_DELAROY_MONTHLY);
        SKU_DELAROY_SIXMONTH=tinyDB.getString(Constants.SKU_DELAROY_SIXMONTH);
        mHelper.enableDebugLogging(true);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @SuppressLint("LongLogTag")
            public void onIabSetupFinished(IabResult result) {
                Log.e(TAG, result.getMessage());
                if (mHelper != null && result.isSuccess()) {
                    try {
                        mHelper.queryInventoryAsync(mGotInventoryListener);
                        Log.e(TAG, "result success");
                    } catch (Exception e) {
                        complain("Error querying inventory. Another async operation in progress.");
                    }
                }

            }
        });
    }


    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {


        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");
            String languages = tinyDB.getString(Constants.Language);
            Constants.languagechange(mContext,languages);
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");


            Purchase delaroyMonthly = inventory.getPurchase(SKU_DELAROY_MONTHLY);
            Purchase delaroySixMonth = inventory.getPurchase(SKU_DELAROY_SIXMONTH);
            if (delaroyMonthly != null && delaroyMonthly.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_MONTHLY;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "one month " + delaroyMonthly.getPurchaseState() + "  " + delaroyMonthly.getPurchaseTime());
            } else if (delaroySixMonth != null && delaroySixMonth.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_SIXMONTH;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "Six month " + delaroySixMonth.getPurchaseState() + "  " + delaroySixMonth.getPurchaseTime());
            } else {
                mDelaroySku = "";
                mAutoRenewEnabled = false;
            }

            mSubscribedToDelaroy = (delaroyMonthly != null && verifyDeveloperPayload(delaroyMonthly))
                    || (delaroySixMonth != null && verifyDeveloperPayload(delaroySixMonth));

            if (mSubscribedToDelaroy) {
                tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                Constants.admob_banner = "";
                Constants.admob_Interstitial = "";
                Constants.admob_nativead = "";
                Constants.admob_rewardad = "";
                Constants.facebook_banner = "";
                Constants.facebook_interstitial = "";
                Constants.facebook_native = "";
                Constants.facebook_rectangle = "";
                Constants.facebooknative_banner = "";
                Log.e(TAG, "issubscribed" + mDelaroySku);
            } else {
                tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                Constants.admob_banner = mContext.getString(R.string.admob_banner);
                Constants.admob_Interstitial = mContext.getString(R.string.admob_Interstitial);
                Constants.admob_nativead = mContext.getString(R.string.admob_nativead);
                Constants.admob_rewardad = mContext.getString(R.string.admob_rewardedad);
                Constants.facebook_banner = mContext.getString(R.string.facebook_banner);
                Constants.facebook_interstitial = mContext.getString(R.string.facebook_interstitial);
                Constants.facebook_native = mContext.getString(R.string.facebook_native);
                Constants.facebook_rectangle = mContext.getString(R.string.facebook_rectangle);
                Constants.facebooknative_banner = mContext.getString(R.string.facebooknative_banner);
                Log.e(TAG, "isNot Subscribed " + mDelaroySku);
            }

        }
    };


    private boolean verifyDeveloperPayload(Purchase p) {
        p.getDeveloperPayload();
        return true;
    }

    void complain(String message) {
        Log.e(TAG, "**** Delaroy Error: " + message);
    }
}
