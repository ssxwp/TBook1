package com.tarena.tbook.model;


/**
 * Created by hanamingming on 16/2/23.
 */
public interface IModel {




	public interface AsyncCallback {

		void onSuccess(Object success);

		void onError(Object error);

	}

}
