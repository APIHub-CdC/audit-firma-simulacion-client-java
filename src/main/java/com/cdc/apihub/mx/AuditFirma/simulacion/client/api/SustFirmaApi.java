package com.cdc.apihub.mx.AuditFirma.simulacion.client.api;

import com.cdc.apihub.mx.AuditFirma.simulacion.client.ApiClient;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.ApiException;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.ApiResponse;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.Configuration;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.Pair;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.ProgressRequestBody;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import com.cdc.apihub.mx.AuditFirma.simulacion.client.model.SustitucionNIPPeticion;
import com.cdc.apihub.mx.AuditFirma.simulacion.client.model.SustitucionNIPRespuesta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SustFirmaApi {
	private ApiClient apiClient;

	public SustFirmaApi() {
		this(Configuration.getDefaultApiClient());
	}

	public SustFirmaApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public okhttp3.Call nipCall(String xApiKey, SustitucionNIPPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody = body;
		String localVarPath = "";
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		if (xApiKey != null)
			localVarHeaderParams.put("x-api-key", apiClient.parameterToString(xApiKey));
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		final String[] localVarAccepts = { "application/json" };
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
		if (localVarAccept != null)
			localVarHeaderParams.put("Accept", localVarAccept);
		final String[] localVarContentTypes = { "application/json" };
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);
		if (progressListener != null) {
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
				@Override
				public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
					okhttp3.Response originalResponse = chain.proceed(chain.request());
					return originalResponse.newBuilder()
							.body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {};
		return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
				localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	private okhttp3.Call nipValidateBeforeCall(String xApiKey, SustitucionNIPPeticion body,
			final ProgressResponseBody.ProgressListener progressListener,
			final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		if (xApiKey == null) {
			throw new ApiException("Missing the required parameter 'xApiKey' when calling nip(Async)");
		}
		if (body == null) {
			throw new ApiException("Missing the required parameter 'body' when calling nip(Async)");
		}

		okhttp3.Call call = nipCall(xApiKey, body, progressListener, progressRequestListener);
		return call;
	}

	public SustitucionNIPRespuesta nip(String xApiKey, SustitucionNIPPeticion body) throws ApiException {
		ApiResponse<SustitucionNIPRespuesta> resp = nipWithHttpInfo(xApiKey, body);
		return resp.getData();
	}
	
	public ApiResponse<?> genericNIP(String xApiKey, SustitucionNIPPeticion body) throws ApiException {
		return nipWithHttpInfo(xApiKey, body);
	}

	public ApiResponse<SustitucionNIPRespuesta> nipWithHttpInfo(String xApiKey, SustitucionNIPPeticion body)
			throws ApiException {
		okhttp3.Call call = nipValidateBeforeCall(xApiKey, body, null, null);
		Type localVarReturnType = new TypeToken<SustitucionNIPRespuesta>() {
		}.getType();
		return apiClient.execute(call, localVarReturnType);
	}

}
