package pe.area51.notepad;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    private final String remoteId;
    private final long localId;
    private final long creationUnixTime;
    private final long modificationUnixTime;
    private final String title;
    private final String content;

    /*
    Debe leerse en el mismo orden en que se escribió.
     */
    public Note(final Parcel parcel) {
        this.remoteId = parcel.readString();
        this.localId = parcel.readLong();
        this.creationUnixTime = parcel.readLong();
        this.modificationUnixTime = parcel.readLong();
        this.title = parcel.readString();
        this.content = parcel.readString();
    }

    public Note(String remoteId, long localId, long creationUnixTime, long modificationUnixTime, String title, String content) {
        this.remoteId = remoteId;
        this.localId = localId;
        this.creationUnixTime = creationUnixTime;
        this.modificationUnixTime = modificationUnixTime;
        this.title = title;
        this.content = content;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public long getLocalId() {
        return localId;
    }

    public long getCreationUnixTime() {
        return creationUnixTime;
    }

    public long getModificationUnixTime() {
        return modificationUnixTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "remoteId: " + remoteId + ",localId: "+localId+ ",creationUnixTime: " + creationUnixTime + ", UnixTime: " + creationUnixTime + ", Title: " + title + ", Content: " + content;
    }

    /*
    Este método debe devolver "0" o "1" (constante
    "Parcelable.CONTENTS_FILE_DESCRIPTOR"). Se utiliza la constante
    "CONTENTS_FILE_DESCRIPTOR" (o 1), cuando se implementa la interfaz
    "Parcelable" con la clase "FileDescriptor". En todos los otros casos,
    debe devolverse 0.
    */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.remoteId);
        dest.writeLong(this.localId);
        dest.writeLong(this.creationUnixTime);
        dest.writeLong(this.modificationUnixTime);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    /*
    El objeto "Creator" es necesario para recrear nuestro objeto
    a partir de un "Parcel". Debe crearse como público y estático.
    */
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(final Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(final int size) {
            return new Note[size];
        }
    };
}
