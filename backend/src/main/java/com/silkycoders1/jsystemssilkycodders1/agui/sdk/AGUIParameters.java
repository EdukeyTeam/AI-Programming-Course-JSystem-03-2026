package com.silkycoders1.jsystemssilkycodders1.agui.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.Context;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.RunAgentParameters;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.State;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.Tool;
import com.silkycoders1.jsystemssilkycodders1.agui.protocol.message.BaseMessage;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AGUIParameters {

    private String threadId;
    private String runId;
    private List<Tool> tools;
    private List<Context> context;
    private Object forwardedProps;
    private List<BaseMessage> messages;
    private State state;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public List<Context> getContext() {
        return context;
    }

    public void setContext(List<Context> context) {
        this.context = context;
    }

    public Object getForwardedProps() {
        return forwardedProps;
    }

    public void setForwardedProps(Object forwardedProps) {
        this.forwardedProps = forwardedProps;
    }

    public List<BaseMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<BaseMessage> messages) {
        this.messages = messages;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RunAgentParameters toRunAgentParameters() {
        return RunAgentParameters.builder()
                .threadId(getThreadId())
                .runId(getRunId())
                .messages(getMessages())
                .tools(getTools())
                .context(getContext())
                .forwardedProps(getForwardedProps())
                .state(getState())
                .build();
    }
}
