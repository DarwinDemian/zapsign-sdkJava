package signers;

import body.signer.SignBatch;
import body.signer.Signer;
import request.Request;
import services.HttpRequestFactory;
import services.JsonConverter;

import java.net.http.HttpResponse;

public class SignerRequest extends Request {

    public SignerRequest(String apiToken) {
        super(apiToken);
    }

    public Signer detailSigner(String signerToken) throws Exception {
        return getRequest("signers/" + signerToken, Signer.class);
    }

    public Signer updateSigner(String signerToken, Signer signer) throws Exception {
        return postRequest(signer, "signers/" + signerToken, Signer.class);
    }

    public Signer addSigner(String docToken, Signer signer) throws Exception {
        return postRequest(signer, "docs/" + docToken + "/add-signer", Signer.class);
    }

    public String deleteSigner(String docToken) throws Exception {
        return deleteRequest("signer/" + docToken + "/remove", String.class);
    }

    public String signInBatch(SignBatch signBatch) throws Exception {
        return postRequest(signBatch, "sign", String.class);
    }
}
