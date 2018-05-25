# anakin.business.domain.raktar

| Element | Name | Documentation |
| ----------- | ------- | ------------- |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RaktarP | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>A BÁV&#160;z&aacute;logt&aacute;rgyak őrz&eacute;s&eacute;re szolg&aacute;l&oacute; rakt&aacute;rai.&#160;Állapot t&iacute;pus&uacute; entit&aacute;s.    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RaktariTetelA | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Fizikai egys&eacute;g (pl. tasak), amelyben t&aacute;rgyakat t&aacute;rolunk. &#160;L&eacute;teznek szabadon &aacute;ll&oacute; t&aacute;rgyak is, de ezeket is megjel&ouml;lj&uuml;k vonalk&oacute;d alapj&aacute;n k&eacute;pzett rakt&aacute;ri azonos&iacute;&iacute;t&oacute;val.    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RaktarMuveletE | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Rakt&aacute;ri műveletek. Esem&eacute;ny t&iacute;pus&uacute; entit&aacute;s.&#160;    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RaktarMuveletTipus |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RovancsF |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsAnyag |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | TetelTipus |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsTargykor |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RovancsTetelF | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Rovancs egy eleme.&#160;    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | Hely |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | TaroloHelyP | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Hely, ahol adott t&aacute;rgyak tal&aacute;lhat&oacute;k.&#160;Állapot t&iacute;pus&uacute; elem. Nem minden t&aacute;rol&oacute;hely k&ouml;tődik rakt&aacute;rhoz (ld. tipus).    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | HolVan |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RaktarTipus |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RovancsUtemezesP | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>V&eacute;grehajthat&oacute; rovancsok t&iacute;pusai. A rovancs&uuml;temez&eacute;s objektumok defini&aacute;lj&aacute;k a rovancsok kezdő időpontj&aacute;t, gyakoris&aacute;g&aacute;t, az &eacute;rintett rakt&aacute;ri t&eacute;telek &eacute;s/vagy t&aacute;rgyak k&ouml;r&eacute;t &eacute;s a rovancs terjedelm&eacute;t.&#160;    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsIsmetlodes |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Adott rakt&aacute;ri t&eacute;telen v&eacute;grehajtott utols&oacute; művelet.&#160;    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RovancsParosF | <html>  <head>		<style>			p {padding:0px; margin:0px;}		</style>	</head>  <body>    <p>Rovancsol&aacute;si műveleteket v&eacute;grehajt&oacute; munkat&aacute;rs p&aacute;ros. Egy t&aacute;rgyellenőrből (rakt&aacute;ros)&#160;&eacute;s egy jegyz&eacute;kellenőrből (tipikusan becs&uuml;s vagy p&eacute;nzt&aacute;ros)&#160;&aacute;ll.&#160;    </p></body></html> |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsTetelEredmeny |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsTipus |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsEredmeny |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | Aktiv_e |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | RovancsTargyF |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.EnumerationImpl` | RovancsTargyEredmeny |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ClassImpl` | integer |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.AssociationImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.impl.CallEventImpl` | - |  |
| `com.nomagic.uml2.ext.magicdraw.classes.mdkernel.impl.ConstraintImpl` | shrtRovret |  |
