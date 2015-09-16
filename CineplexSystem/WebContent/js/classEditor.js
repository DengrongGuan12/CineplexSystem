    // dom 元素处理
    // 获取dom属性
    function getAttr( attr )
    {
        return this.getAttribute( attr );
    }
    // 设置属性
    function setAttr( attr, val )
    {
        this.setAttribute( attr, val );
 
        return this;
    };
    // 编辑元素class
    function editClass( mode, data )
    {
        var cls = getAttr.call( this, "class" ) || '';
 
        var arr = cls.split( /\s+/ );
 
        switch( mode )
        {
            case "add":
 
                return setAttr.call( this, "class", cls + " " + data );
 
            break;
 
            case "remove":
 
                for( var i = 0; i < arr.length; i++ )
                {
                    if( arr[ i ] == data )
                    {
                        arr.splice( i, 1 );
                    }
                };
 
                var cls = arr.join( " " );
 
                cls = cls.replace( /^\s|\s$/g, "" );
 
                // cls = cls == "" ? null : cls;
 
                return setAttr.call( this, "class", cls );
 
            break;
 
            default:
 
                console.log( "EditClass mode error!" );
 
                return this;
 
            break;
        }
    }
    // 添加class
    function addClass( cls )
    {
        return editClass.call( this, "add", cls );
    }
    // 删除class
    function removeClass( cls )
    {
        return editClass.call( this, "remove", cls );
    }
     
    // 我写的这个调用起来和普通的调用方式不一样， 需要用call
     