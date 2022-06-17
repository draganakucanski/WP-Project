package beans;

import com.google.gson.annotations.SerializedName;

public enum Role {
	    @SerializedName("admin")
	    ADMIN, 
	    @SerializedName("manager")
	    MANAGER,
	    @SerializedName("trainer")
	    TRAINER, 
	    @SerializedName("customer")
	    CUSTOMER
}
