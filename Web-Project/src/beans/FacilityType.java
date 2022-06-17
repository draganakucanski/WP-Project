package beans;

import com.google.gson.annotations.SerializedName;

public enum FacilityType {
	@SerializedName("gym")
    GYM,
    @SerializedName("pool")
    POOL,
    @SerializedName("sportsCenter")
    SPORTSCENTER,
    @SerializedName("danceStudio")
    DANCESTUDIO
}
