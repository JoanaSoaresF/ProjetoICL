package types;

import java.util.List;
import java.util.stream.Collectors;

public class TypeClosure implements IType {

    private List<IType> parametersTypes;
    private IType returnType;

    public TypeClosure(List<IType> parametersTypes, IType returnType) {
        this.parametersTypes = parametersTypes;
        this.returnType = returnType;
    }

    @Override
    public String showType() {
        return "closure";
    }

    @Override
    public String loadPrefix() {
        return "a";
    }

    public List<IType> getParametersTypes() {
        return parametersTypes;
    }

    public IType getReturnType() {
        return returnType;
    }

    public String getInterfaceName() {
        String paramsString = parametersTypes.stream()
                .map(t -> t.showType())
                .collect(Collectors.joining("_"));

        return String.format("closure_interface_%s_%s", paramsString, returnType.showType());

    }

    public String getApplySignature() {
        String paramsString = parametersTypes.stream()
                .map(t -> paramType(t.show()))
                .collect(Collectors.joining(""));

        return "(" + paramsString + ")" + returnType.show();
    }

    private String paramType(String type) {
        if (type.equals(show())) {
            return "L" + show() + ";";
        } else {
            return type;
        }
    }

    @Override
    public String show() {
//        String paramsString = parametersTypes.stream()
//                .map(type -> type.show())
//                .collect(Collectors.joining(", "));
//
//        return String.format("(%s)%s", paramsString, returnType.show());
        return "java/lang/Object";
    }
}
