package de.wolff.sample;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.TEXT_HTML)
public class ServletResourceMapper implements MessageBodyWriter<Viewable> {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Viewable o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Viewable o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        Object model = o.getModel();
        String path = o.getPath();

        if (model != Viewable.NO_MODEL){
            request.setAttribute("model", model);
        }

        try {
            final PrintWriter out = new PrintWriter(entityStream);
            request.getRequestDispatcher(path).forward(request, new WriterDecorator(response, out));
            out.flush();
        } catch (ServletException e){
            throw new IOException("Exception will processing page " + path);
        }
    }

    private class WriterDecorator extends HttpServletResponseWrapper {

        private final PrintWriter out;

        WriterDecorator(HttpServletResponse response, PrintWriter out) {
            super(response);
            this.out = out;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return out != null ? out : super.getWriter();
        }
    }
}
