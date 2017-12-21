# Installing

## SDK 3.0

* Run for SDK3.0 alfresco startup

```bash
mvn clean install alfresco:run
```

## AMPs modules

* for building AMPs modules: 

```bash
mvn clean package
```

You can find AMPs here `.CustomizeLoginBackground-platform/target/CustomizeLoginBackground-platform.amp` and `.CustomizeLoginBackground-share/target/CustomizeLoginBackground-share.amp`

## Instruction

* login as the admin user to Alfresco Share. Open:`Admin Tools`/`Tools`/`Login Background` -  Sample URL for locally installed Alfresco: [http://localhost:8080/share/page/console/admin-console/CustomizeLoginBackground-config-form](http://localhost:8080/share/page/console/admin-console/CustomizeLoginBackground-config-form) 
* Choose an image from your PC, choose any background color (will used for filling free space that will not use image, if you choose some of `Displaying modes` that can leave a free space on the screen because of image scaling)
* click `Submit`. After successful you will see a preview of the image.

## Advanced Instruction

* You don't have to upload an image if you want to fill out all background with solid color. So, you can choose only `Background Color` and click `Submit`. In this case `Background Image Display Mode` value will not matter.

* Display Modes: 
    *  `Fill` - fill out the image by smaller side (can cut the image, you will not see selected `Background Color`). 
    *  `Aoto fit` - fit the image automatically (you can see free space which will be filled by selected `Background Color`). 
    *  `Fit by height` - fit the image by height, image will be scaled by 100% of height (you can see free vertical spaces which will be filled by selected `Background Color`).
    *  `Fit by width` - fit the image by width, image will be scaled by 100% of width (you can see free horizontal spaces which will be filled by selected `Background Color`). 
    *  `Sretch` - fit the image by browser window with distortion of geometry (you will not see selected `Background Color` and image will not be cut).
    *  `Tile` -  image will be repeated many times if it is a small one (you will not see selected `Background Color`).
    *  `Center` -  image will be centered on browser window (you can see free horizontal and vertical spaces which will be filled by selected `Background Color`).
        
*  **NOTE: If upload a smaller image than login form and select a `Center` mode - the image will be hidden by this form and you can not see it.** 

![Sample Video](https://ecm.flex-solution.com/share/proxy/alfresco/slingshot/node/content/workspace/SpacesStore/1ff1e213-fbd4-4b06-9255-b99a2f416a7f/Login%20Customizer%20V1.mp4)
