package org.narender.OpenAI;

import java.util.List;
import java.util.Optional;
import org.narender.OpenAI.OpenAiModel;


public record CreateAssistantRequest(
    String model,
    Optional<String> name,
    Optional<String> description,
    Optional<String> instructions
){
    public static Builder newBuilder() {
        return new Builder();
    }
    public static class Builder{
        private static final String DEFAULT_MODEL = OpenAiModel.GPT_3_5_TURBO.getId();

        private String model = DEFAULT_MODEL;

        private Optional<String> name = Optional.empty();
        private Optional<String> description = Optional.empty();
        private Optional<String> instructions = Optional.empty();

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder model(OpenAiModel model) {
            this.model = model.getId();
            return this;
        }


        public Builder name(String name) {
            this.name = Optional.of(name);
            return this;
        }

        public Builder description(String description) {
            this.description = Optional.of(description);
            return this;
        }


        public Builder instructions(String instructions) {
            this.instructions = Optional.of(instructions);
            return this;
        }

        public CreateAssistantRequest build() {
            return new CreateAssistantRequest(
                    model,
                    name,
                    description,
                    instructions);
        }
    };
}
