package Shared;

public class JsonInstruction {

    private String json;
    private String instruction;

    public JsonInstruction(String json, String instruction) {
        this.json = json;
        this.instruction = instruction;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "JsonInstruction{" +
                "json='" + json + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
