该文件所记录的事件为点击事件的事件序列传播过程，即按钮从按下到抬起过程所打印的日志信息。

## 目录

- 返回返回父类同名方法
- 返回true
    1. Activity返回true
    2. ViewGroup返回true
    3. View返回true
- 返回false
    1. Activity返回false
    2. ViewGroup返回false
    3. View返回false

### 1. 返回父类同名方法，例如：return super.dispatchTouchEvent(ev);
>注Activity、ViewGroup、View的dispatchTouchEvent()、onInterceptTouchEvent和onTouchEvent()返回的都是父类同名方法。他们默认返回为false，

正常情况下的日志信息
```
//这是按下的日志
I/EventActivity:    dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:  dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:  onInterceptTouchEvent():ACTION_DOWN
I/TestView:         dispatchTouchEvent():ACTION_DOWN
                    onTouchEvent():ACTION_DOWN
I/TestFrameLayout:  onTouchEvent():ACTION_DOWN
I/EventActivity:    onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:    dispatchTouchEvent():ACTION_UP
                    onTouchEvent():ACTION_UP
```

view设置了onTouch的日志信息
```
//这是按下的日志                 
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
    onInterceptTouchEvent():ACTION_DOWN
I/TestView: dispatchTouchEvent():ACTION_DOWN
I/EventActivity: TestView组件setOnTouchListener事件的:onTouch()():ACTION_DOWN
I/TestView: onTouchEvent():ACTION_DOWN
I/TestFrameLayout: onTouchEvent():ACTION_DOWN
I/EventActivity: onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity: dispatchTouchEvent():ACTION_UP
    onTouchEvent():ACTION_UP
```
view控件设置了onClick和日志信息

```
//这是按下的日志                 
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
    onInterceptTouchEvent():ACTION_DOWN
I/TestView: dispatchTouchEvent():ACTION_DOWN
    onTouchEvent():ACTION_DOWN
//这是抬起的日志                 
I/EventActivity: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: onInterceptTouchEvent():ACTION_UP
I/TestView: dispatchTouchEvent():ACTION_UP
    onTouchEvent():ACTION_UP
I/EventActivity: TestView组件setOnClickListener()事件的:onClick()
```
view控件设置了onClick和onTouch的日志信息
```
//这是按下的日志                 
I/EventActivity:    dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:  dispatchTouchEvent():ACTION_DOWN
                  onInterceptTouchEvent():ACTION_DOWN
I/TestView:       dispatchTouchEvent():ACTION_DOWN
I/EventActivity:   TestView组件setOnTouchListener事件的:onTouch()():ACTION_DOWN
I/TestView:       onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:    dispatchTouchEvent():ACTION_UP
I/TestFrameLayout:  dispatchTouchEvent():ACTION_UP
                  onInterceptTouchEvent():ACTION_UP
I/TestView:       dispatchTouchEvent():ACTION_UP
I/EventActivity:   TestView组件setOnTouchListener事件的:onTouch()():ACTION_UP
I/TestView:       onTouchEvent():ACTION_UP
I/EventActivity:   TestView组件setOnClickListener()事件的:onClick()
```

### 2. 返回true

#### 1. Activity返回true

***dispatchTouchEvent返回true***
`事件dispatchTouchEvent中被消费，不再分发`
- 事件将不会往下传播
- activity的onTounchEvent不会被调用

```
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/EventActivity: dispatchTouchEvent():ACTION_UP
```
***onTouchEvent返回true***
`activity 的OTouchEvent一般情况下，只有在ViewGroup、View`
- 不会影响事件向下传播，即ViewGroup和View都有机会处理该事件
```
//这是按下的日志                 
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
    onInterceptTouchEvent():ACTION_DOWN
I/TestView: dispatchTouchEvent():ACTION_DOWN
I/EventActivity: TestView组件setOnTouchListener事件的:onTouch()():ACTION_DOWN
I/TestView: onTouchEvent():ACTION_DOWN
I/TestFrameLayout: onTouchEvent():ACTION_DOWN
I/EventActivity: onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity: dispatchTouchEvent():ACTION_UP
    onTouchEvent():ACTION_UP
```

#### 2. ViewGroup返回true

***dispatchTouchEvent返回true***
- Activity、ViewGroup、View的onTouchEvent事件都将不会被调用。
- ViewGroup的onInterceptTouchEvent事件也不会被调用。
- 事件不会向view传播
```
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
I/EventActivity: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: dispatchTouchEvent():ACTION_UP
```

***onInterceptTouchEvent返回true***
>注 当我们点击ViewGroup的空白区域(未点击它的字View)走的日志也和下面相同。

- 事件被拦截，事件将交给ViewGroup自己的onTouchEvent处理，处理结果分为两种情况，第一种是onTouchEvent返回的为true，第二种是onTouchEvent返回的为false(同名父类默认返回为false)
- 事件不会向view传播
- ViewGroup的onTouchEvent只能接受到事件序列的第一个事件，后续事件将不会分发给它
- 后续事件将交给Activity的onTouchEvent处理
```
//按下日志
I/EventActivity:     dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:   dispatchTouchEvent():ACTION_DOWN
                   onInterceptTouchEvent():ACTION_DOWN
                   onTouchEvent():ACTION_DOWN
I/EventActivity:    onTouchEvent():ACTION_DOWN
//抬起日志
I/EventActivity:    dispatchTouchEvent():ACTION_UP
                   onTouchEvent():ACTION_UP
```

***onTouchEvent返回true***
- 回传的事件被ViewGroup的onTouchEvent消费了，不再回传到Activity的onTouchEvent
- 后续的其他事件都将交给ViewGroup的onTouchEvent来处理，不再传给他的子view
```
//这是按下的日志                 
I/EventActivity:     dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:   dispatchTouchEvent():ACTION_DOWN
                   onInterceptTouchEvent():ACTION_DOWN
I/TestView:         dispatchTouchEvent():ACTION_DOWN
                   onTouchEvent():ACTION_DOWN
I/TestFrameLayout:   onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:     dispatchTouchEvent():ACTION_UP
I/TestFrameLayout:   dispatchTouchEvent():ACTION_UP
                   onTouchEvent():ACTION_UP
```

#### 3. View返回true

***dispatchTouchEvent返回true***
- Activity、ViewGroup、View的onTouchEvent都不会被调用,即事件在view的dispatchTouchEvent被消耗了
- 后续事件也会分发过来

```
//按下日志
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: onInterceptTouchEvent():ACTION_DOWN
I/TestView: dispatchTouchEvent():ACTION_DOWN
//抬起日志
I/EventActivity: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: dispatchTouchEvent():ACTION_UP
                 onInterceptTouchEvent():ACTION_UP
I/TestView: dispatchTouchEvent():ACTION_UP
```

***onTouchEvent返回true***
- Activity、ViewGroup、onTouchEvent都不会被调用，即事件不会回传
```
//按下日志
I/EventActivity:   dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: onInterceptTouchEvent():ACTION_DOWN
I/TestView:        dispatchTouchEvent():ACTION_DOWN
I/TestView:        onTouchEvent():ACTION_DOWN
//抬起日志
I/EventActivity: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: dispatchTouchEvent():ACTION_UP
I/TestFrameLayout: onInterceptTouchEvent():ACTION_UP
I/TestView: dispatchTouchEvent():ACTION_UP
I/TestView: onTouchEvent():ACTION_UP
```


### 3. 返回false

#### 1. Activity返回false

***dispatchTouchEvent返回false***

- 和dispatchTouchEvent返回true结果一样

```
I/EventActivity: dispatchTouchEvent():ACTION_DOWN
I/EventActivity: dispatchTouchEvent():ACTION_UP
```

***onTouchEvent返回false***
- 和onTouchEvent返回true结果一样

```
//这是按下的日志                 
I/EventActivity:   dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
                 onInterceptTouchEvent():ACTION_DOWN
I/TestView:       dispatchTouchEvent():ACTION_DOWN
I/TestView:        onTouchEvent():ACTION_DOWN
I/TestFrameLayout: onTouchEvent():ACTION_DOWN
I/EventActivity:   onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:   dispatchTouchEvent():ACTION_UP
                 onTouchEvent():ACTION_UP
```

#### 2. ViewGroup返回false

***dispatchTouchEvent返回false***

- 事件将不会在向下传递，ViewGroup的onInterceptTouchEvent和onTouchEvent不会调用。
- 后续事件将会交给Activity的dispatchTouchEvent和onTouchEvent处理
```
//按下日志
I/EventActivity:   dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
I/EventActivity:   onTouchEvent():ACTION_DOWN
//抬起日志
                 dispatchTouchEvent():ACTION_UP
                 onTouchEvent():ACTION_UP
```

***onInterceptTouchEvent返回false***
- 和返回同名方法打印的日志信息结果相同
- 不会调用自己的onTouchEvent
- 不会拦截该事件，事件将会传递给子view
```
//这是按下的日志                 
I/EventActivity:   dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
                 onInterceptTouchEvent():ACTION_DOWN
I/TestView:       dispatchTouchEvent():ACTION_DOWN
I/TestView:        onTouchEvent():ACTION_DOWN
I/TestFrameLayout: onTouchEvent():ACTION_DOWN
I/EventActivity:   onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:   dispatchTouchEvent():ACTION_UP
                 onTouchEvent():ACTION_UP
```

***onTouchEvent返回false***
- view的onTouchEvent将不会被调用
- 事件将会以次回传首先会调用ViewGroup的onTouchEvent方法然后调用Activity的onTouchEvent方法。
- 后续事件将不会分发，而是交给了Activity的onTouchEvent方法处理。
```
//这是按下的日志                 
I/EventActivity:   dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout: dispatchTouchEvent():ACTION_DOWN
                 onInterceptTouchEvent():ACTION_DOWN
I/TestView:       dispatchTouchEvent():ACTION_DOWN
I/TestView:        onTouchEvent():ACTION_DOWN
I/TestFrameLayout: onTouchEvent():ACTION_DOWN
I/EventActivity:   onTouchEvent():ACTION_DOWN
//这是抬起的日志
I/EventActivity:   dispatchTouchEvent():ACTION_UP
                 onTouchEvent():ACTION_UP
```

#### 3. View返回false

***dispatchTouchEvent返回false***

- view的onTouchEvent将不会被调用
- 事件将会以次回传首先会调用ViewGroup的onTouchEvent方法然后调用Activity的onTouchEvent方法。
- 后续事件将不会分发，而是交给了Activity的onTouchEvent方法处理。

```
//按下日志
I/EventActivity:      dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:    dispatchTouchEvent():ACTION_DOWN
                    onInterceptTouchEvent():ACTION_DOWN
I/TestView:          dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:    onTouchEvent():ACTION_DOWN
I/EventActivity:      onTouchEvent():ACTION_DOWN
//抬起日志
I/EventActivity:      dispatchTouchEvent():ACTION_UP
                    onTouchEvent():ACTION_UP
```

***onTouchEvent返回false***
- view的onTouchEvent只能接收到事件序列的第一个事件
- 事件将会回传，以次会传到ViewGroup-->Activity的onTouchEvent中
- 事件序列的后续事件将不会传递，而是交给Activity的onTouchEvent来处理

```
//按下日志
I/EventActivity:     dispatchTouchEvent():ACTION_DOWN
I/TestFrameLayout:   dispatchTouchEvent():ACTION_DOWN
                   onInterceptTouchEvent():ACTION_DOWN
I/TestView:         dispatchTouchEvent():ACTION_DOWN
I/TestView:         onTouchEvent():ACTION_DOWN
I/TestFrameLayout:   onTouchEvent():ACTION_DOWN
I/EventActivity:    onTouchEvent():ACTION_DOWN
//抬起日志
                   dispatchTouchEvent():ACTION_UP
I/EventActivity:    onTouchEvent():ACTION_UP
```
