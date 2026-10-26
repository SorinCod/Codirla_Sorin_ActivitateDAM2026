package ro.ase.dam.lab04;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AparatFoto implements Parcelable {

    private String marca;
    private String model;
    private int rezolutieMP;
    private boolean stabilizare;
    private TipAparat tip;
    private String senzor;
    private float rating;
    private boolean wifi;

    public AparatFoto(String marca, String model, int rezolutieMP, boolean stabilizare,
                      TipAparat tip, String senzor, float rating, boolean wifi) {
        this.marca = marca;
        this.model = model;
        this.rezolutieMP = rezolutieMP;
        this.stabilizare = stabilizare;
        this.tip = tip;
        this.senzor = senzor;
        this.rating = rating;
        this.wifi = wifi;
    }

    protected AparatFoto(Parcel in) {
        marca = in.readString();
        model = in.readString();
        rezolutieMP = in.readInt();
        stabilizare = in.readByte() != 0;
        tip = TipAparat.valueOf(in.readString());
        senzor = in.readString();
        rating = in.readFloat();
        wifi = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(model);
        dest.writeInt(rezolutieMP);
        dest.writeByte((byte) (stabilizare ? 1 : 0));
        dest.writeString(tip.name());
        dest.writeString(senzor);
        dest.writeFloat(rating);
        dest.writeByte((byte) (wifi ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AparatFoto> CREATOR = new Creator<AparatFoto>() {
        @Override
        public AparatFoto createFromParcel(Parcel in) {
            return new AparatFoto(in);
        }

        @Override
        public AparatFoto[] newArray(int size) {
            return new AparatFoto[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRezolutieMP() {
        return rezolutieMP;
    }

    public void setRezolutieMP(int rezolutieMP) {
        this.rezolutieMP = rezolutieMP;
    }

    public boolean isStabilizare() {
        return stabilizare;
    }

    public void setStabilizare(boolean stabilizare) {
        this.stabilizare = stabilizare;
    }

    public TipAparat getTip() {
        return tip;
    }

    public void setTip(TipAparat tip) {
        this.tip = tip;
    }

    public String getSenzor() {
        return senzor;
    }

    public void setSenzor(String senzor) {
        this.senzor = senzor;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @NonNull
    @Override
    public String toString() {
        return "AparatFoto{" +
                "marca='" + marca + '\'' +
                ", model='" + model + '\'' +
                ", rezolutieMP=" + rezolutieMP +
                ", stabilizare=" + stabilizare +
                ", tip=" + tip +
                ", senzor='" + senzor + '\'' +
                ", rating=" + rating +
                ", wifi=" + wifi +
                '}';
    }
}