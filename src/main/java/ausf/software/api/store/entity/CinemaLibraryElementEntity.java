/*
 * Copyright © 2022 Shcherbina Daniil and BouH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ausf.software.api.store.entity;

import ausf.software.api.store.CinemaCategory;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * Описывает элемент из таблицы, содержащей информацию о рекомендуемых
 * одногрупниками фильмах.
 *
 * @see CinemaCategory
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "cinema_library")
public class CinemaLibraryElementEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "title")
    @NotNull
    private String title;

    @Column (name = "category")
    @NotNull
    private int category;

    @Column (name = "description")
    private String description;

    @Column (name = "recommended")
    private String recommendedBy;

    public CinemaLibraryElementEntity(@NotNull String title, @NotNull int category, String description, String recommendedBy) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.recommendedBy = recommendedBy;
    }

    public CinemaLibraryElementEntity() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(@NotNull int category) {
        this.category = category;
    }
}
