package beans;

import com.google.gson.annotations.SerializedName;

public enum TrainingType {
	@SerializedName("group")
    GROUP, 
    @SerializedName("personal")
    PERSONAL,
    @SerializedName("gym")
    GYM
}
