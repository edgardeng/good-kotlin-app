## Some Layout Widget in Android App
> Android应用中一些常用的布局控件

### LinerLayout 线性布局
> 性布局控件，它包含的子控件将以横向或竖向的方式排列

主要属性：
  * orientation 显示方式: HORIZONTAL(0)、VERTICAL(1)
  * layout_weight 权重: 默认0 ，表示按照widgets或者是containers实际大小来显示，若高于0的值，则将 Container剩余可用空间分割，分割大小具体取决于每一个widget或者是 container的layout_weight及该权值在所有widgets或者是containers中的比例
  * baselineAligned 是否允许用户调整它内容的基线: true/false
  * baselineAlignedChildIndex 当前LinearLayout与其它View的对齐方式:   
  * gravity 对齐方式(本元素里的所有元素的重力方向)。其值有：top、bottom、left、right、center_vertical、fill_vertical、center_horizontal、fill_horizontal、center、fill、clip_vertical、clip_horizontal
  * layout_gravity 相对于父元素的重力方向
  * measureWithLargestChild 当被设置为真时,所有的子控件将被认为是具有权重最小面积最大的子控件

### RelativeLayout 相对布局
    
_子控件的主要属性_：
  * layout_centerInParent 相对于父元素完全居中
  * layout_centerHorizontal 水平居中
  * layout_centerVertical 垂直居中
  * layout_alignParentBottom 贴紧父元素的下边缘
  * layout_alignParentLeft 贴紧父元素的左边缘 
  * layout_alignParentRight 贴紧父元素的右边缘 
  * layout_alignParentTop 贴紧父元素的上边缘 
  * layout_below      在某元素的下方 
  * layout_above      在某元素的的上方 
  * layout_toStartOf  在某元素的左边 
  * layout_toEndOf  在某元素的右边
  * layout_alignTop  本元素的上边缘和某元素的的上边缘对齐 
  * layout_alignStart 本元素的左边缘和某元素的的左边缘对齐 
  * layout_alignBottom 本元素的下边缘和某元素的的下边缘对齐 
  * layout_alignEnd  本元素的右边缘和某元素的的右边缘对齐

### FrameLayout 帧布局
> 帧布局的容器中无论放入多少个控件，控件默认情况下左上角都对齐到容器的左上角，如果控件一样大，同一时间只能见到最上面

和RelativeLayout类似

### TableLayout 表格布局
> 和GridLayout的区别是GridLayout只能制定每一列宽度一样的表格布局，而TableLayout能够制定各列宽度不一样的表格布局。

主要属性:

  * collapseColumns: ”0,1” 隐藏第0列和第1列
  * stretchColumns: ”0,1” 第0列和第1列可以向行方向扩展
  * stretchColumns: ”*” 所有列可以向行方向扩展
  * shrinkColumns: ”0,1” 第0列和第1列可以向列方向扩展 
  
_子控件的主要属性_：

  * layout_column: ”1” 该控件显示在第1列
  * layout_span: ”2” 该控件占据2列


### ConstraintLayout 约束布局
> 类 androidx.constraintlayout.widget.ConstraintLayout
> ConstraintLayout是RelativeLayout的升级版，它允许我们在Layout上设计子控件与子控件之间的位置关系，而且它远比RelativeLayout在Android Studio中表现的效果更灵活、更容易使用。

在项目需要引用依赖
```
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
```

__子控件主要属性__

  * layout_constraintLeft_toLeftOf	把A的left side放在B的left side（左边对齐）
  * layout_constraintLeft_toRightOf	把A的left side放在B的right side（左边相对右边对齐）
  * layout_constraintRight_toLeftOf	把A的right side放在B的left side（右边相对左边对齐）
  * layout_constraintRight_toRightOf 把A的right side放在B的right side（右边对齐）
  * layout_constraintTop_toTopOf	把A的top side放在B的top side（顶部对齐）
  * layout_constraintTop_toBottomOf	把A的top side放在B的bottom side（顶部相对底部对齐）
  * layout_constraintBottom_toTopOf	把A的bottom side放在B的top side（底部相对顶部对齐）
  * layout_constraintBottom_toBottomOf	把A的bottom side放在B的bottom side（底部对齐）
  * layout_constraintStart_toEndOf	把A的start position放在B的end position（起始位置相对结束位置对齐）
  * layout_constraintStart_toStartOf	把A的start position放在B的start position（起始位置对齐）
  * layout_constraintEnd_toStartOf	把A的end position放在B的start position（结束位置相对起始位置对齐）
  * layout_constraintEnd_toEndOf	把A的end position放在B的end position（结束位置对齐）
  * layout_constraintBaseline_toBaselineOf	把A的bottom side放在B的top side（基准线对齐）
  
__偏斜(Bias)__
> 在使用LinearLayout时，会使用Gravity来将水平或者垂直排列的控件按照权重的分配进行排列。而在ConstraintLayout中，它提供了bias属性来对控件进行权重的分配。

  * layout_constraintHorizontal_bias	水平方向偏移系数
  * layout_constraintVertical_bias	垂直方向偏移系数

__隐藏__
> 当在ConstraintLayout中，若一个控件隐藏（GONE）之后，它会变成一个点，因此对于把该控件作为参考控件的其它控件依然具有约束作用。

  * layout_goneMarginLeft	隐藏控件左边距
  * layout_goneMarginRight	隐藏控件右边距
  * layout_goneMarginTop	隐藏控件顶部边距
  * layout_goneMarginBottom	隐藏控件底部边距
  * layout_goneMarginStart	隐藏控件起始边距
  * layout_goneMarginEnd	隐藏控件结束边距

__宽高比__

  * layout_constraintDimensionRatio ="16:6" 属性设置一个比例即可(先将宽或者高设置为0dp/MATCH_CONSTRAINT)
  * layout_constraintWidth_default="percent" // 设置宽为百分比 (相对父控件)
  * layout_constraintWidth_percent="0.3" // 0到1之间的值
  * layout_constraintHeight_default="percent" // 设置高为百分比 
  * layout_constraintHeight_percent="0.3" // 0到1之间的值 
  
__Chain属性__

> 链条在同一个轴上（水平或者垂直）提供一个类似群组的统一表现
>
> 如果一组小部件通过双向连接（见图，显示最小的链，带有两个小部件），则将其视为链条
>
> 当在链的第一个元素上设置属性 layout_constraintHorizontal_chainStyle 或layout_constraintVertical_chainStyle 时，链的行为将根据指定的样式（默认为CHAIN_SPREAD）而更改
   
  * layout_constraintHorizontal_chainStyle	水平方向上的Chain: 展开(spread)、内部展开(spread_inside)、包裹(packed)
  * layout_constraintVertical_chainStyle	垂直方向上的Chain: 
  * Spread链模式: 把空间平均分配开来，每个View占有各自的平分空间，它是Chain属性的默认模式。
        可以配合weight属性设置spread的权重，在设置权重的时候，我们需要将控件的width或者height设置成0dp，并设置layout_constraintHorizontal_weight或者layout_constraintVertical_weight的值：
  * Spread Inside链模式: 把两边最边缘的两个View到外向父组件边缘的距离去除，然后让剩余的Views在剩余的空间内部平分空间。
  * Packed链模式: 将所有Views聚拢在一起，控件和控件之间不留间隙，并将聚拢之后的Views居中显示。
  
### Guideline
> 类 androidx.constraintlayout.widget.Guideline
>
> GuideLine只是用在ConstraintLayout布局里面的一个工具类，用于辅助布局，类似于辅助线，可以设置android:orientation属性来确定是横向还是纵向。

>重要的是Guideline是不会显示到界面上的，默认是GONE的。
 
主要属性:
 * orientation	方向
 * layout_constraintGuide_begin	以View的起始位置为参照物，水平或垂直方向上边界(dp)
 * layout_constraintGuide_end	以View的结束位置为参照物，水平或垂直方向上边界(dp)
 * layout_constraintGuide_percent 水平或垂直方向上的百分比(float ratio 0.0f - 1.0f)

### CoordinatorLayout 协调布局
> androidx.coordinatorlayout.widget.CoordinatorLayout
> 协调子控件的组件，可以解决绝大部分滑动联动问题

### Reference 参考

* [ConstraintLayout 属性详解 和Chain的使用](https://www.jianshu.com/p/50debc330e91)
 
* [AndroidX CoordinatorLayout文档](https://developer.android.google.cn/reference/androidx/coordinatorlayout/widget/package-summary) 

* [AndroidX ConstraintLayout文档](https://developer.android.google.cn/reference/androidx/constraintlayout/widget/ConstraintLayout)

* [CoordinatorLayout的基本使用](https://www.jianshu.com/p/4cf2e99cf491)
