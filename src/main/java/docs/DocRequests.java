package docs;

import body.doc.*;
import request.Request;
import response.DocAsyncResponse;
import response.DocResponse;
import response.DocsResponse;
import response.ExtraDocResponse;
import services.HttpRequestFactory;
import services.JsonConverter;

import java.net.http.HttpResponse;

public class DocRequests extends Request {

    public DocRequests(String apiToken) {
        super(apiToken);
    }

    public DocResponse createDocFromUploadPdf(DocFromPdf doc) throws Exception {
        return createRequest(doc, "docs", DocResponse.class);
    }

    public DocResponse createDocFromUploadDocx(DocFromDocx doc) throws Exception {
        return createRequest(doc, "docs", DocResponse.class);
    }

    public DocAsyncResponse createDocFromUploadAsync(DocFromPdf doc) throws Exception {
        return createRequest(doc, "docs/async", DocAsyncResponse.class);
    }

    public DocResponse createDocFromDocxBase64(DocFromDocxBase64 doc) throws Exception {
        return createRequest(doc, "docs", DocResponse.class);
    }

    public DocResponse createDocFromPdfBase64(DocFromPdfBase64 doc) throws Exception {
        return createRequest(doc, "docs", DocResponse.class);
    }

    public DocAsyncResponse createDocFromPdfBase64Async(DocFromPdfBase64 doc) throws Exception {
        return createRequest(doc, "docs/async", DocAsyncResponse.class);
    }

    public DocResponse createDocFromTemplate(DocFromTemplate doc) throws Exception {
        return createRequest(doc, "models/create-doc", DocResponse.class);
    }

    public DocAsyncResponse createDocFromTemplateAsync(DocFromTemplate doc) throws Exception {
        return createRequest(doc, "models/create-doc/async", DocAsyncResponse.class);
    }

    public ExtraDocResponse addExtraDoc(String docToken, ExtraDoc extraDoc) throws Exception {
        return createRequest(extraDoc, "docs/" + docToken + "upload-extra-doc", ExtraDocResponse.class);
    }

    public DocResponse detailDoc(String docToken) throws Exception {
        return createRequest(null, "docs/" + docToken, DocResponse.class);
    }

    public DocsResponse getDocs() throws Exception {
        return createRequest(null, "docs", DocsResponse.class);
    }

    public DocResponse deleteDoc(String docToken) throws Exception {
        return createRequest(null, "docs/" + docToken, DocResponse.class);
    }

    public int placeSignatures(String docToken, RubricaList rubricaList) throws Exception {
        String jsonDoc = new JsonConverter().convertToJson(rubricaList);
        String uri = apiRoute + "docs/" + docToken + "/place-signatures/?api_token=" + apiToken;
        HttpResponse<String> response = new HttpRequestFactory().postRequest(uri, jsonDoc);
        return response.statusCode();
    }
}
