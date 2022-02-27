/*
 * Copyright (C) 2018 The Tachiyomi Open Source Project
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.ireader.domain.catalog.interactor

import org.ireader.domain.catalog.service.CatalogStore
import org.ireader.domain.models.entities.CatalogLocal

class TogglePinnedCatalog(
    private val store: CatalogStore,
) {

    suspend fun await(catalog: CatalogLocal) {
        store.togglePinnedCatalog(catalog.sourceId)
    }

}