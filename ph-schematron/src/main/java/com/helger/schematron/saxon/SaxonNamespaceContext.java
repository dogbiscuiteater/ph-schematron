/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.schematron.saxon;

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.namespace.NamespaceContext;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.xml.namespace.MapBasedNamespaceContext;

import net.sf.saxon.om.NamespaceResolver;

public final class SaxonNamespaceContext implements NamespaceContext, NamespaceResolver
{
  private final MapBasedNamespaceContext m_aCtx;

  public SaxonNamespaceContext (@Nonnull final MapBasedNamespaceContext aCtx)
  {
    m_aCtx = ValueEnforcer.notNull (aCtx, "Ctx");
  }

  @Nullable
  public String getURIForPrefix (@Nullable final String sPrefix, final boolean bUseDefault)
  {
    if (bUseDefault && StringHelper.hasNoText (sPrefix))
      return m_aCtx.getDefaultNamespaceURI ();
    return m_aCtx.getCustomNamespaceURI (sPrefix);
  }

  @Nonnull
  public Iterator <String> iteratePrefixes ()
  {
    final ICommonsList <String> aList = new CommonsArrayList <> (m_aCtx.getPrefixToNamespaceURIMap ().keySet ());
    aList.add ("");
    return aList.iterator ();
  }

  @Nonnull
  public String getNamespaceURI (@Nonnull final String sPrefix)
  {
    return m_aCtx.getNamespaceURI (sPrefix);
  }

  @Nullable
  public String getPrefix (@Nonnull final String sNamespaceURI)
  {
    return m_aCtx.getPrefix (sNamespaceURI);
  }

  @Nonnull
  public Iterator <?> getPrefixes (@Nonnull final String sNamespaceURI)
  {
    return m_aCtx.getPrefixes (sNamespaceURI);
  }
}