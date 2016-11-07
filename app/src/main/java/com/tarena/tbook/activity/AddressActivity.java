package com.tarena.tbook.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tarena.tbook.R;
import com.tarena.tbook.adapter.AddressAdapter;
import com.tarena.tbook.entity.Address;
import com.tarena.tbook.presenter.IAddressPresenter;
import com.tarena.tbook.presenter.impl.AddressPresenter;
import com.tarena.tbook.view.AddressDialog;
import com.tarena.tbook.view.IAddressView;

public class AddressActivity extends Activity implements IAddressView{

	@ViewInject(R.id.lvAddress)
	private ListView listView;
	private Dialog dialog;
	private IAddressPresenter presenter;
	private AddressAdapter adapter;
	private List<Address> addresss=new ArrayList<Address>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		x.view().inject(this);
		presenter = new AddressPresenter(this);
		//加载所有地址信息
		presenter.listAddress();
	}

	/**
	 * 添加监听
	 *
	 * @param view
	 */
	public void doClick(View view) {
		switch (view.getId()) {
			case R.id.btnAddAddress:
				showAddAddressDialog();
				break;
		}
	}

	/**
	 * 弹出添加地址对话框
	 */
	private void showAddAddressDialog() {
		dialog = new AddressDialog(this,new AddressDialog.Callback() {
			@Override
			public void onSubmit(Address address) {
				presenter.saveAddress(address);
			}
		});
		dialog.show();
	}

	@Override
	public void dismissSaveAddressDialog() {
		Toast.makeText(this,"地址保存成功",Toast.LENGTH_SHORT).show();
		dialog.dismiss();
		presenter.listAddress();
	}

	@Override
	public void setAddresss(List<Address> address) {
		if(addresss == null) {
			this.addresss = address;
		}else {
			this.addresss.clear();
			addresss.addAll(address);
		}
	}

	@Override
	public void setAdapter() {
		if(adapter == null){
			adapter = new AddressAdapter(this.addresss, this);
			adapter.setPresenter(presenter);
			listView.setAdapter(adapter);
			return;
		}else {
			adapter.notifyDataSetChanged();
		}
	}

}
