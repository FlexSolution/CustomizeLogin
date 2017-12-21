<import resource="classpath:alfresco/templates/webscripts/org/alfresco/repository/forms/form.post.js">

    function main2() {
        // Extract template args
        var itemId = decodeURIComponent(url.templateArgs["item_id"]);

        // persist the submitted data using the most appropriate data set
        if (typeof formdata !== "undefined") {

            var saveImageField = null;
            var deleteImage = false;

            for (var i = 0; i < formdata.fields.length; i++) {
                if (formdata.fields[i].name == "prop_fslb_backgroundImage") {
                    saveImageField = formdata.fields[i];
                }
                if (formdata.fields[i].name == "prop_fslb_deleteBackgroundImage" && formdata.fields[i].value == "true") {
                    deleteImage = true;
                }
            }

            if (deleteImage) {
                var deleteNode = search.findNode(itemId.replace("/", "://")); // back to usual NodeRef
                deleteNode.properties["fslb:backgroundImage"].delete();
                deleteNode.properties["fslb:deleteBackgroundImage"] = false;// set it back to false
                deleteNode.save();
            }

            if (!deleteImage && saveImageField && saveImageField.value.length() > 0) {
                var node = search.findNode(itemId.replace("/", "://")); // back to usual NodeRef
                node.properties["fslb:backgroundImage"].guessMimetype(saveImageField.filename);
                node.properties["fslb:backgroundImage"].write(saveImageField.content);
                node.properties["fslb:backgroundImage"].guessEncoding();
                node.save();
            }
        }
    };

main2();
