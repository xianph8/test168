
## Android 调用 系统相机的代码
未编辑完。。。

    Android调用系统相机拍照
	http://stormzhang.com/android/2013/10/22/android-select-system-photos/
	Android调用系统图库
	http://stormzhang.com/android/2013/10/19/android-call-camera/

```java
public void onClick(View v) {
	switch (v.getId()) {
		case R.id.camera:
			Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// 下面这句指定调用相机拍照后的照片存储的路径
			intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), Constant.save_user_photo)));
			startActivityForResult(intentCamera, 2);
			bottomPhotoDialog.dismiss();
			break;
		case R.id.photo:
			Intent intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(intent, 1);
			bottomPhotoDialog.dismiss();
			break;
		case R.id.cancle:
			bottomPhotoDialog.dismiss();
			break;
		}
	}
		裁剪参数
	private void startPhoto(Uri url) {
		Intent intent = new Intent();
		intent.putExtra(Util.IMAGE_URI, url);
		intent.putExtra(Util.CROP_IMAGE_WIDTH, 300);
		intent.putExtra(Util.CROP_IMAGE_HEIGHT, 300);
		intent.putExtra(Util.CIRCLE_CROP, false);
		intent.setClass(this, CropActivity.class);
		startActivityForResult(intent, 3);
	}
	activity回调
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		// 如果是直接从相册获取
		case 1:
			if (data == null) {
				return;
			}
			Uri uri = data.getData();
			if (uri != null) {
			        startPhoto(uri);
			}
			break;
		// 如果是调用相机拍照时
		case 2:
			picture = new File(Environment.getExternalStorageDirectory() + "/" + Constant.save_user_photo);
			if (!picture.exists()) {
				return;
			}
			Uri uri2 = Uri.fromFile(picture);
			if (uri2 != null) {
				startPhoto(uri2);
			}
			break;
		// 取得裁剪后的图片
		case 3:
			if (data != null) {
				bitmap = BitmapFactory.decodeFile(data.getStringExtra(Util.CROP_IMAGE_PATH));
				user_photo.setImageBitmap(bitmap);
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
```