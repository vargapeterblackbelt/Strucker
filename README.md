#set($location = $template.getOutputLocation())
##![alt text]($imagePath "Meme")
#foreach($class in $Class)

#[[# ]]#$class.getName()

$class.documentation

#if(!$class.attribute.isEmpty())
#[[## ]]# Attributes
#foreach($attribute in $class.attribute)
- **$attribute.name** : $attribute.type.name [$attribute.multiplicity]
#end
#end

#if(!$class.getOwnedOperation().isEmpty())
#[[## ]]# Methods
#foreach($operation in $class.getOwnedOperation())
#set($fname = $file.create('learnjudoMethods.vm', "${operation.getQualifiedName().replace('::','/')}.md", [$operation, $location]))
- [**$operation.name**#[[()]]#]($fname)
#end
#end
#end

#foreach($enum in $Enumeration)

#[[# ]]#$enum.getName()

$enum.documentation

#[[## ]]#Literals
#foreach($literal in $enum.getOwnedLiteral())
- **$literal.getName()**
#end

#[[## ]]# Methods
#foreach($operation in $enum.getOwnedOperation())
#set($fname = $file.create('learnjudoMethods.vm', "${operation.getQualifiedName().replace('::','/')}.md", [$operation, $location]))
- [**$operation.name**#[[()]]#]($fname)
#end
#end
