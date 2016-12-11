/*
 * Project Name: framework-core-ext
 * File Name: Order.java
 * Class Name: Order
 *
 * Copyright 2015 Servyou Software Group Co., Ltd.
 *
 * Licensed under the Servyou
 *
 * http://www.servyou.com.cn
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luosoy.frame.web.page;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 方向.
     */
    public enum Direction {

        /**
         * 递增.
         */
        ASC,
        /**
         * 递减.
         */
        DESC;

        /**
         * 从String中获取Direction.
         *
         * @param value 值
         * @return String对应的Direction
         */
        public static Direction fromString(String value) {
            return Direction.valueOf(value.toLowerCase());
        }
    }

    /**
     * 默认方向.
     */
    private static final Direction DEFAULT_DIRECTION = Direction.DESC;

    /**
     * 属性 .
     */
    private String property;

    /**
     * 方向.
     */
    private Direction direction = DEFAULT_DIRECTION;

    /**
     * 初始化一个新创建的Order对象.
     */
    public OrderDto() {
    }

    /**
     * @param property 属性
     * @param direction 方向
     */
    public OrderDto(String property, Direction direction) {
        this.property = property;
        this.direction = direction;
    }

    /**
     * 返回递增排序.
     *
     * @param property 属性
     * @return 递增排序
     */
    public static OrderDto asc(String property) {
        return new OrderDto(property, Direction.ASC);
    }

    /**
     * 返回递减排序.
     *
     * @param property 属性
     * @return 递减排序
     */
    public static OrderDto desc(String property) {
        return new OrderDto(property, Direction.DESC);
    }

    /**
     * 获取属性.
     *
     * @return 属性
     */
    public String getProperty() {
        return property;
    }

    /**
     * 设置属性.
     *
     * @param property 属性
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * 获取方向.
     *
     * @return 方向
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * 设置方向.
     *
     * @param direction 方向
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * (non-Javadoc).
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        OrderDto other = (OrderDto) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getDirection(), other.getDirection()).isEquals();
    }

    /**
     * (non-Javadoc).
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getDirection()).toHashCode();
    }

}
