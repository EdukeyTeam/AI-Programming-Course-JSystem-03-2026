package com.silkycoders1.jsystemssilkycodders1.agui.protocol;

import com.silkycoders1.jsystemssilkycodders1.agui.protocol.message.BaseMessage;

import java.util.List;

public class RunAgentParameters {

    private final String threadId;
    private final String runId;
    private final List<Tool> tools;
    private final List<Context> context;
    private final Object forwardedProps;
    private final List<BaseMessage> messages;
    private final State state;

    private RunAgentParameters(Builder builder) {
        this.threadId = builder.threadId;
        this.runId = builder.runId;
        this.tools = builder.tools;
        this.context = builder.context;
        this.forwardedProps = builder.forwardedProps;
        this.messages = builder.messages;
        this.state = builder.state;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getThreadId() {
        return threadId;
    }

    public String getRunId() {
        return runId;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public List<Context> getContext() {
        return context;
    }

    public Object getForwardedProps() {
        return forwardedProps;
    }

    public List<BaseMessage> getMessages() {
        return messages;
    }

    public State getState() {
        return state;
    }

    public static class Builder {
        private String threadId;
        private String runId;
        private List<Tool> tools;
        private List<Context> context;
        private Object forwardedProps;
        private List<BaseMessage> messages;
        private State state;

        public Builder threadId(String threadId) {
            this.threadId = threadId;
            return this;
        }

        public Builder runId(String runId) {
            this.runId = runId;
            return this;
        }

        public Builder tools(List<Tool> tools) {
            this.tools = tools;
            return this;
        }

        public Builder context(List<Context> context) {
            this.context = context;
            return this;
        }

        public Builder forwardedProps(Object forwardedProps) {
            this.forwardedProps = forwardedProps;
            return this;
        }

        public Builder messages(List<BaseMessage> messages) {
            this.messages = messages;
            return this;
        }

        public Builder state(State state) {
            this.state = state;
            return this;
        }

        public RunAgentParameters build() {
            return new RunAgentParameters(this);
        }
    }
}
