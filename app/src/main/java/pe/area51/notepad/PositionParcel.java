package pe.area51.notepad;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumno on 9/12/15.
 */
public class PositionParcel implements Parcelable {

    private String latitud;
    private String longitud;
    private String hour;

    public PositionParcel(String latitud, String longitud, String hour) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.hour = hour;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
