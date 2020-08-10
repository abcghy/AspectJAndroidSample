# A Sample for AspectJ in Android

## Activity Lifecycle hook
You can use AspectJ to hook the onCreate or onResume etc.

## Annotation hook
You can define a Annotation first, such as `@CheckVIP` to check if user have vip permission.
If they have, you can let them proceed. If not, alert them or bring them to the vip subscription page.

## Glide usage hook
We all know that `Bitmap` is like the largest memory usage object in Android. So we have to be careful about that. Nowadays, CDN have some suffix strings to make image smaller than the original image.
We can hook the Glide's `ImageViewTarget.setResource` method. And analyse the request image's size, the view's size. If request's size is larger than view's. Then we have some bad code.
