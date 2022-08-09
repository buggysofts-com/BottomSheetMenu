# BottomSheetMenu [![Release](https://jitpack.io/v/buggysofts-com/BottomSheetMenu.svg)](https://jitpack.io/#buggysofts-com/BottomSheetMenu)
A powerful &amp; customizable menu implementation for android. It supports any level of nested menu structures. Follow the steps below to import the library to your project. You will also find some sample codes.
<br />
<br />
## Import

#### Add JitPack repository to your project level build.gradle file
```
...

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Or, in newer android projects, if you need to the add repository in settings.gradle file...
```
...

dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Finally add this dependency to your app/module level build.gradle file
```
...

dependencies {
    ...
    implementation 'com.github.buggysofts-com:BottomSheetMenu:v1.0.2'
}
```
And you are done importing the library.
<br />
<br />
## Sample codes
To create a minimal bottom sheet menu...
```
BottomSheetMenu bottomSheetMenu = new BottomSheetMenu(
  MainActivity.this,
  R.menu.sample_menu,
  new BottomSheetMenu.MenuItemClickListener() {
      @Override
      public void onClick(MenuItem item) {
          Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
      }
  }
).show();
```
You can use methods that follow builder pattern to set properties of different components of the menu. For example the following code snippet sets background, divider, menu icon place holder (in case a menu item does not have an icon), expand icon (for indicating submenu of a menu item), a title and finally it shows the menu.
```
bottomSheetMenu
  .menuBackground(
      AppCompatResources.getDrawable(
        MainActivity.this,
        R.drawable.menu_bg
      )
  )
  .dividerDrawable(
      BottomSheetMenu.getSystemDefaultDivider(
          MainActivity.this
      )
  )
  .menuIconPlaceHolder(
      AppCompatResources.getDrawable(
          MainActivity.this,
          R.drawable.ic_image
      )
  )
  .menuExpandIcon(
      AppCompatResources.getDrawable(
          MainActivity.this,
          R.drawable.ic_image
      )
  )
  .menuTitle("Main menu")
  .show();
```
