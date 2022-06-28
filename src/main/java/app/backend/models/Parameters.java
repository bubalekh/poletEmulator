package app.backend.models;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    private final List<Parameter> parameterList;

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public Parameters() {
        parameterList = new ArrayList<>();
    }
}
