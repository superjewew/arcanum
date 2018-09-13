package com.mahavira.arcanum.store.domain.entity;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;
import org.parceler.Parcels;

/**
 * Created by norman on 05/09/18.
 *
 */

@Parcel
public class SetVisitParam {

    @ParcelProperty("userEmail")
    private final String userEmail;

    @ParcelProperty("storeEmail")
    private final String storeEmail;

    @ParcelConstructor
    public SetVisitParam(@ParcelProperty("userEmail") final String user, @ParcelProperty("storeEmail") final String store) {
        userEmail = user;
        storeEmail = store;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public byte[] marshall() {
        Parcelable parcelable = Parcels.wrap(this);
        android.os.Parcel parcel = android.os.Parcel.obtain();
        parcelable.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }

    public static android.os.Parcel unmarshall(byte[] bytes) {
        android.os.Parcel parcel = android.os.Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0); // This is extremely important!
        return parcel;
    }

    public static SetVisitParam unmarshall(byte[] bytes, Creator<SetVisitParam$$Parcelable> creator) {
        android.os.Parcel parcel = unmarshall(bytes);
        SetVisitParam result = creator.createFromParcel(parcel).getParcel();
        parcel.recycle();
        return result;
    }

}
