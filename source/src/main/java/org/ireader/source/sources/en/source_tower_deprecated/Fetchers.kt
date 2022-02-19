package org.ireader.source.sources.en.source_tower_deprecated

import kotlinx.serialization.Serializable


/**
 * @param endpoint the endpint of site: sample:"https://freewebnovel.com"
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Latest(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val linkSelector: String? = null,

    val linkAtt: String? = null,

    val nameSelector: String? = null,

    val nameAtt: String? = null,

    val coverSelector: String? = null,

    val coverAtt: String? = null,

    val supportPageList: Boolean = false,

    val maxPageIndex: Int? = null,

    ) : Fetcher

/**
 * @param endpoint the endpint of site: sample:"https://freewebnovel.com"
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Popular(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val linkSelector: String? = null,

    val linkAtt: String? = null,

    val nameSelector: String? = null,

    val nameAtt: String? = null,

    val coverSelector: String? = null,

    val coverAtt: String? = null,
) : Fetcher

/**
 * @param endpoint the endpint of site: sample:"/latest-release-novel/{page}/""
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Detail(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val nameSelector: String? = null,

    val nameAtt: String? = null,

    val coverSelector: String? = null,

    val coverAtt: String? = null,

    val descriptionSelector: String? = null,

    val descriptionBookAtt: String? = null,

    val authorBookSelector: String? = null,

    val authorBookAtt: String? = null,

    val categorySelector: String? = null,

    val categoryAtt: String? = null,
) : Fetcher

/**
 * @param endpoint the endpint of site: sample:"https://freewebnovel.com"
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Search(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val linkSelector: String? = null,

    val linkAtt: String? = null,

    val nameSelector: String? = null,

    val nameAtt: String? = null,

    val coverSelector: String? = null,

    val coverAtt: String? = null,
) : Fetcher

/**
 * @param endpoint the endpint of site: sample:"https://freewebnovel.com"
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Chapters(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val isDownloadable: Boolean = false,

    val chaptersEndpointWithoutPage: String? = null,

    val isChapterStatsFromFirst: Boolean = true,

    val linkSelector: String? = null,

    val linkAtt: String? = null,

    val nameSelector: String? = null,

    val nameAtt: String? = null,

    val supportNextPagesList: Boolean = false,

    val subStringSomethingAtEnd: String? = null,
) : Fetcher

/**
 * @param endpoint the endpint of site: sample:"https://freewebnovel.com"
 * @param ajaxSelector if the site is loaded by ajax, type the first item that is loaded by ajax.
 * @param isHtmlType ishtmlResponse, type false if is json type
 * @param isGetRequestType is it Get Request, type false if it is Post Request
 * @param addBaseUrlToLink should add the base url to the first part of link,
 *      for example the link you get from site is some thing like this "/chapters/ and
 *      if you type true here the base url for example  is  https://freewebnovel.com
 *      will be added to the link
 * @param openInWebView type true if you want the content to be open in webview
 * @param selector the general selector that cover all detail for one book
 * @param nextPageSelector the css selector for the next page, it must contain a value, to let the app that next page exist.
 *
 */
@Serializable
data class Content(

    override val endpoint: String? = null,

    override val ajaxSelector: String? = null,

    override val isGetRequestType: Boolean = true,

    override val isHtmlType: Boolean = true,

    override val selector: String? = null,

    override val addBaseUrlToLink: Boolean = false,

    override val openInWebView: Boolean = false,

    override val nextPageSelector: String? = null,

    override val nextPageAtt: String? = null,

    override val nextPageValue: String? = null,

    override val addToStringEnd: String? = null,

    override val idSelector: String? = null,

    override val addBaseurlToCoverLink: Boolean = false,

    val pageTitleSelector: String? = null,

    val pageTitleAtt: String? = null,

    val pageContentSelector: String? = null,

    val pageContentAtt: String? = null,
) : Fetcher


interface Fetcher {
    val endpoint: String?
    val ajaxSelector: String?
    val isHtmlType: Boolean
    val isGetRequestType: Boolean
    val selector: String?
    val addBaseUrlToLink: Boolean
    val addToStringEnd: String?
    val idSelector: String?
    val openInWebView: Boolean
    val nextPageSelector: String?
    val nextPageAtt: String?
    val nextPageValue: String?
    val addBaseurlToCoverLink: Boolean?
}

sealed class FetchType(val index: Int) {
    object LatestFetchType : FetchType(0)
    object PopularFetchType : FetchType(1)
    object SearchFetchType : FetchType(2)
    object DetailFetchType : FetchType(3)
    object ContentFetchType : FetchType(4)
    object ChapterFetchType : FetchType(5)
}


