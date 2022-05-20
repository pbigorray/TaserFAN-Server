package server.model;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JSonTranformer<T>implements ResponseTransformer {

        private Gson gson = new Gson();

        @Override
        public String render(Object model) {
            return gson.toJson(model);
        }
        public T getObject(String json,Class<T> clas){
            return gson.fromJson(json,clas);
        }
}
