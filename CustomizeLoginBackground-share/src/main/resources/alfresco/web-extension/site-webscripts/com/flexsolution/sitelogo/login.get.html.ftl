<@markup id="background-css" target="css" action="after">
<#-- CSS Dependencies -->
    <@link href="CustomizeLoginBackground-share/components/cssextensione-login/CustomizeLoginBackground.css" group="login"/>
</@markup>

<@markup id="reset-pass-js" target="js" action="after">
<#-- JavaScript Dependencies -->
    <@script src="CustomizeLoginBackground-share/components/js/customize-login/CustomizeLoginBackground.js" group="login"/>
    <script type="text/javascript">
        new FlexSolution.component.CustomizeLoginBackground("${args.htmlid}");
    </script>
</@markup>