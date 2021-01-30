package flow.resolver.request;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonRequestResolver extends RequestResolver{

    @Override
    protected String getInputData(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try(InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                }
            }
        }

        return stringBuilder.toString();
    }
}
