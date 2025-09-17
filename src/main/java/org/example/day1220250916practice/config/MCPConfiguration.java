package org.example.day1220250916practice.config;

import org.example.day1220250916practice.service.TodoService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MCPConfiguration {

    @Bean
    public ToolCallbackProvider toolCallbackProvider(TodoService todoService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(todoService)
                .build();
    }

}
