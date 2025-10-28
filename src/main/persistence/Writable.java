package persistence;

import org.json.JSONObject;

// adapted from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
