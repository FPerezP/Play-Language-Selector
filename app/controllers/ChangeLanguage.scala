package controllers

import play.api.mvc.{Action, Controller}
import play.api.i18n.Lang
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import play.api.Play.current

trait ChangeLanguage extends Controller {

  val localeForm = Form("locale" -> nonEmptyText)

  val changeLocale = Action { implicit request =>
    val referrer = request.headers.get(REFERER).getOrElse(HOME_URL)
    localeForm.bindFromRequest.fold(
      errors => {
        BadRequest(referrer)
      },
      locale => {
        Redirect(referrer).withLang(Lang(locale)) // TODO Check if the lang is handled by the application
      })
  }

  protected val HOME_URL = "/"
}

