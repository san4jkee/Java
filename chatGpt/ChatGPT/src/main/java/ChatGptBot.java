import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import java.io.IOException;

public class ChatGptBot {
    private static final String ENGINE = "text-davinci-003";


    public static String generateResponse(String prompt, int maxTokens) throws IOException {
        OpenAiService service = new OpenAiService("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");

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model(ENGINE)
                .maxTokens(maxTokens)
                .temperature(0.0)
                .topP(1.0)
                .frequencyPenalty(0.2)
                .presencePenalty(0.0)
                //.user("testing")
                .build();
        CompletionResult result = service.createCompletion(completionRequest);

        return result.getChoices().get(0).getText();

    }
}
