package utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateDeserializer implements JsonDeserializer < LocalDate > {
	
	@Override
	public LocalDate deserialize(JsonElement arg0, java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		// TODO Auto-generated method stub
		return LocalDate.parse(arg0.getAsString(),
	            DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH));
	}
}