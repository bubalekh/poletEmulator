package app.backend.models;

import java.util.ArrayList;
import java.util.List;

public class ParametersWrapper {

    private ParametersWrapper() { };

    private static final ParametersWrapper INSTANCE = new ParametersWrapper();

    private final List<Parameter> parameterList = new ArrayList<>();

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public static ParametersWrapper getInstance() {
        return INSTANCE;
    }
}
