## User Story1

作为车主，我可以停车并获得一张停车票，然后可以使用停车票取车

### AC1: 车主停车成功

Examples：
- Given 有空位的停车场，When 停车，Then 停车成功

### AC2: 车主停车失败

Examples:
- Given 没有空位的停车场，When 停车，Then 停车失败

### AC3: 车主取车成功

Examples:
- Given 有效停车票，When 取车，Then 取车成功

### AC4: 车主取车失败

Examples：
- Given 无效停车票，When 取车，Then 取车失败

## User Story2

作为停车场Owner，我希望车辆优先停放到剩余空位最多的停车场

### AC1: 车辆停放在剩余空车位最多的停车场

Examples:

- Given 停车场A和B，A剩余1个车位，B剩余2个车位，When 停车，Then A剩余1个车位，B剩余1个车位
- Given 停车场A和B，A剩余2个车位，B剩余2个车位，When 停车，Then A剩余1个车位，B剩余2个车位