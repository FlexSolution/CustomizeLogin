<import resource="classpath:alfresco/templates/webscripts/org/alfresco/repository/forms/form.post.js">

    function main2() {

        var IMAGE_PROP_NAME = "fslb:backgroundImage";
        var DELETE_IMAGE_FLAG_PROP_NAME = "fslb:deleteBackgroundImage";

        // Extract template args
        var itemId = decodeURIComponent(url.templateArgs["item_id"]);
        var nodeRefString = itemId.replace("/", "://"); // back to usual NodeRef

        var getFormDataKeyFormatted = function (propName) {
            return propName.replace(":","_")
        };

        // persist the submitted data using the most appropriate data set
        if (typeof formdata !== "undefined") {

            var saveImageField = null;
            var deleteImage = false;

            for (var i = 0; i < formdata.fields.length; i++) {
                if (formdata.fields[i].name == "prop_" + getFormDataKeyFormatted(IMAGE_PROP_NAME)) {
                    saveImageField = formdata.fields[i];
                }
                if (formdata.fields[i].name == "prop_" + getFormDataKeyFormatted(DELETE_IMAGE_FLAG_PROP_NAME) &&
                    formdata.fields[i].value == "true") {
                    deleteImage = true;
                }
            }

            if (deleteImage) {
                var deleteNode = search.findNode(nodeRefString);
                deleteNode.properties[IMAGE_PROP_NAME].delete();
                deleteNode.properties[DELETE_IMAGE_FLAG_PROP_NAME] = false;// set it back to false
                deleteNode.save();
            }

            if (!deleteImage && saveImageField && saveImageField.value.length() > 0) {
                var node = search.findNode(nodeRefString);
                node.properties[IMAGE_PROP_NAME].guessMimetype(saveImageField.filename);
                node.properties[IMAGE_PROP_NAME].write(saveImageField.content);
                node.properties[IMAGE_PROP_NAME].guessEncoding();
                node.save();
            }
        }
    };

main2();
