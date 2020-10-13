import abc


class Queue(abc.ABC):
    @abc.abstractmethod
    def isEmpty(self):
        pass

    @abc.abstractmethod
    def push(self, x):
        pass

    @abc.abstractmethod
    def pop(self):
        pass


class LL_Queue(Queue):
    def __init__(self):
        self.tail = LL_Queue_Cell()
        self.head = self.tail

    def isEmpty(self):
        return self.head.value == None

    def push(self, value):
        self.tail.value = value
        self.tail.next = LL_Queue_Cell()
        self.tail = self.tail.next

    def pop(self):
        if self.isEmpty():
            raise IndexError
        else:
            value = self.head.value
            self.head = self.head.next
            return value


class LL_Queue_Cell:
    def __init__(self):
        self.next = None
        self.value = None