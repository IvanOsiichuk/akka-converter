package org.akkaconverter
package controllers

import com.google.inject.{Inject, Singleton}
import org.akkaconverter.services.ConverterService

import scala.concurrent.ExecutionContext

@Singleton // TODO check need @
class ConverterController @Inject()(implicit val ex: ExecutionContext, converterService: ConverterService) {}
