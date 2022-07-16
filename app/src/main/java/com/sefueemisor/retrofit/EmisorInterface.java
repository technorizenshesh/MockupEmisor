package com.sefueemisor.retrofit;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface EmisorInterface {

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> signupUser(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("check_password")
    Call<ResponseBody> chkPassUser(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> userLogin (@FieldMap Map<String, String> params);


    @Multipart
    @POST("update_profile")
    Call<ResponseBody> profileUpdate(
            @Part("user_name") RequestBody user_name,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile,
            @Part("country_code") RequestBody country_code,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon,
            @Part("user_id") RequestBody user_id,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("get_profile")
    Call<ResponseBody> getUserProfile(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("forgot_password")
    Call<ResponseBody> userForgotPass(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("social_login")
    Call<ResponseBody> socialLogin (@FieldMap Map<String, String> params);



    @GET("get_document_type")
    Call<ResponseBody> getDocTypes ();


    @FormUrlEncoded
    @POST("add_receiver_details")
    Call<ResponseBody> addReceiverInfoApiCall (@FieldMap Map<String, String> params);





  /*  @FormUrlEncoded
    @POST("loginwithpass")
    Call<LoginModel> loginWithPass (@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("loginOtpVerification")
    Call<LoginModel> loginWithOtp (@FieldMap Map<String, String> params);

    @Multipart
    @POST("addShop")
    Call<Map<String,String>> uploadShopInfo(
            @Part("shopName") RequestBody shopName,
            @Part("description") RequestBody description,
            @Part("open_at") RequestBody open_at,
            @Part("close_at") RequestBody close_at,
            @Part("seller_id") RequestBody seller_id,
            @Part("price") RequestBody price,
            @Part("pkgmsg") RequestBody pkgmsg,
            @Part("timebreakstart") RequestBody timebreakstart,
            @Part("timebreakend") RequestBody timebreakend,
            @Part("vat") RequestBody vat,
            @Part("total") RequestBody total,
            @Part("type") RequestBody type,
            @Part("color_id") RequestBody color_id,
            @Part MultipartBody.Part file,
            @Part MultipartBody.Part file2);


    @FormUrlEncoded
    @POST("addLocation")
    Call<Map<String,String>> AddLocation (@FieldMap Map<String, String> params);


    @Multipart
    @POST("addDocument")
    Call<Map<String,String>> uploadDocument(
            @Part("seller_id") RequestBody seller_id,
            @Part MultipartBody.Part file);





    @GET("addColor")
    Call<ColorModel> getColorCode ();

    @Multipart
    @POST("addFabric")
    Call<Map<String,String>> uploadFabric(
    @Part("f_name") RequestBody f_name,
    @Part("color_name") RequestBody color_name,
    @Part("color_id") RequestBody color_id,
    @Part("f_desc") RequestBody f_desc,
    @Part("f_price") RequestBody f_price,
    @Part("seller_id") RequestBody seller_id,
    @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("forgetPass")
    Call<Map<String, String>> forgotPass (@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("forgetPass")
    Call<Map<String, String>> forgotPass1 (@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("changePass")
    Call<Map<String, String>> resetpass (@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("chkOtp")
    Call<Map<String, String>>  chkOtp(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("changePass")
    Call<Map<String, String>> changePassword(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("logout")
    Call<Map<String, String>> logout(@FieldMap Map<String, String> params);


    @Multipart
    @POST("addProfile")
    Call<LoginModel> editName(@HeaderMap Map<String, String> token,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("seller_id") RequestBody seller_id,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("addGender")
    Call<LoginModel> changeGender(@Header("Authorization") String auth,@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("editPhone")
    Call<LoginModel> changeNumber(@Header("Authorization") String auth,@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("editPass")
    Call<Map<String, String>> changePass(@Header("Authorization") String auth,@FieldMap Map<String, String> params);



    @GET("zone")
    Call<ZoneModel> getZoneList();


    @FormUrlEncoded
    @POST("getPackaged")
    Call<PackegeModel> getAllPackgs(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("deletePackage")
    Call<Map<String,String>> deletePackg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("addPackage")
    Call<Map<String,String>> addPackg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);



    @FormUrlEncoded
    @POST("addPackage_json")
    Call<Map<String,String>> addMultiPackg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);






    @GET("tax_detail")
    Call<Map<String,String>> getVat(*//*@Header("Authorization") String auth*//*);






    @FormUrlEncoded
    @POST("getOrderDetails")
    Call<BookingModel> getCurrentRequest(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("updateOrder")
    Call<AcceptCancelModel> aceptCancelledBooking(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("getNotification")
    Call<NotificationModel> getAllNotification(@Header("Authorization") String auth, @FieldMap Map<String,String> params);


    @GET("get_percent")
    Call<OfferModel> getOffers(@Header("Authorization") String auth);



    @Multipart
    @POST("addOffer")
    Call<Map<String,String>> addOff(
            @HeaderMap Map<String, String> token,
            @Part("min_price") RequestBody min_price,
            @Part("max_price") RequestBody max_price,
            @Part("start_date") RequestBody start_date,
            @Part("end_date") RequestBody end_date,
            @Part("offer_perc") RequestBody offer_perc,
            @Part("shopName") RequestBody shopName,
            @Part("seller_id") RequestBody seller_id,
            @Part("shop_id") RequestBody shop_id,
            @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("getOffer")
    Call<OfferAllModel> getAllOfferrrr(@Header("Authorization") String auth, @FieldMap Map<String, String> params);



    @FormUrlEncoded
    @POST("deleteOffer")
    Call<Map<String,String>> deleteOffer(@Header("Authorization") String auth, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("get_vacation")
    Call<VacationModel> getAllVacation(@Header("Authorization") String auth, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("add_vacation")
    Call<Map<String,String>> AddVacationsss(@Header("Authorization") String auth, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("get_selected_slot")
    Call<SlotsModel> getAllAddedslot(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("selectSlot")
    Call<AllSlotModel> getAllslot(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("add_slot")
    Call<Map<String,String>> addSlot(@Header("Authorization") String auth, @FieldMap Map<String, String> params);



    @FormUrlEncoded
    @POST("set_time_for_order_completion")
    Call<Map<String,String>> setDateTime(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("set_order_completion")
    Call<Map<String,String>> bookingFinish(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("getUserHistory")
    Call<HistoryModel> getHistory(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("sellerSendNotification")
    Call<Map<String,String>> sendPushNotification(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("sellerSendAdminMessage")
    Call<Map<String,String>> sendAdminMsg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("sellerGetAdminMessage")
    Call<GetAllMsgModel> getmsgs(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("sellerUnreadCountMessage")
    Call<Map<String,String>> conterMsg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("sellerReadChatMessage")
    Call<Map<String,String>> readconterMsg(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("seller_language_set")
    Call<LoginModel> changeLang (@Header("Authorization") String auth,@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("getColorr")
    Call<ColorModel1> getAllColor1(@Header("Authorization") String auth, @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("getColorr")
    Call<ColorModel1> getAllColor2( @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("updateAddress")
    Call<LoginModel> updateAddressss(@Header("Authorization") String auth,@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("earning_data")
    Call<YearModel> getYearEarning(@Header("Authorization") String auth,@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("delete_vacation")
    Call<Map<String,String>> vacDelete(@Header("Authorization") String auth, @FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("delete_slot")
    Call<Map<String,String>> slotDelete(@Header("Authorization") String auth, @FieldMap Map<String, String> params);



    @FormUrlEncoded
    @POST("add_bank_detail")
    Call<AddBank> addBank(@FieldMap Map<String, String> params);
    //Call<Object> payAirTime(@HeaderMap Map<String,String> header,@Body Map<String, String> body);

    @FormUrlEncoded
    @POST("get_bank_detail")
    Call<GetBank> getBank(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("delete_bank_detail")
    Call<Map<String,String>> deleteBank(@FieldMap Map<String, String> params);

*/



}
