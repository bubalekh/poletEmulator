package app.backend.models;

import app.backend.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ParametersWrapper {

    private ParametersWrapper() { };

    public static Parameters INSTANCE = new Parameters();
    private final List<Parameter> parameterList = new ArrayList<>();

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public static Parameters getInstance() {
        return INSTANCE;
    }
}
