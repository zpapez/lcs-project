package cz.zpapez.lcs;

import java.util.List;

import org.springframework.stereotype.Component;

import cz.zpapez.lcs.model.DiffModel;

@Component
public class DiffDecorator {

    public String renderHtmlDiff(List<DiffModel> listDiffs) {

        StringBuilder sb = new StringBuilder();

        for (DiffModel diffModel : listDiffs) {
            switch (diffModel.getType()) {
            case ADDED:
                sb.append("<span style=\"color:green\">");
                sb.append(diffModel.getStringBuilder());
                sb.append("</span>");
                break;
            case REMOVED:
                sb.append("<span style=\"color:red\">");
                sb.append(diffModel.getStringBuilder());
                sb.append("</span>");
                break;
            default:
                sb.append(diffModel.getStringBuilder());
                break;
            }
        }

        return sb.toString();
    }


}
