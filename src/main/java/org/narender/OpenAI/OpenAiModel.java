package org.narender.OpenAI;

public enum OpenAiModel {
    GPT_4("gpt-4"),
    GPT_4_TURBO("gpt-4-turbo"),
    GPT_4_TURBO_PREVIEW("gpt-4-turbo-preview"),
    GPT_4_1106_PREVIEW("gpt-4-1106-preview"),
    GPT_4_VISION_PREVIEW("gpt-4-vision-preview"),
    GPT_4_32K("gpt-4-32k"),

    // GPT-3.5 Turbo (https://platform.openai.com/docs/models/gpt-3-5-turbo)
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_3_5_TURBO_1106("gpt-3.5-turbo-1106");

    private final String id;

    OpenAiModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
