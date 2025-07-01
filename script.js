class Calculator {
    constructor() {
        this.currentInput = '0';
        this.previousInput = '';
        this.operator = '';
        this.waitingForOperand = false;
        this.history = '';
        this.scientificMode = false;
        
        this.display = {
            current: document.getElementById('current'),
            history: document.getElementById('history')
        };
        
        this.updateDisplay();
        this.setupKeyboardEvents();
    }
    
    // 更新显示
    updateDisplay() {
        this.display.current.textContent = this.formatNumber(this.currentInput);
        this.display.history.textContent = this.history;
    }
    
    // 格式化数字显示
    formatNumber(num) {
        if (num === 'Error') return num;
        
        const number = parseFloat(num);
        if (isNaN(number)) return num;
        
        // 处理很大或很小的数字
        if (Math.abs(number) > 999999999 || (Math.abs(number) < 0.000001 && number !== 0)) {
            return number.toExponential(6);
        }
        
        // 限制小数位数
        if (number % 1 !== 0) {
            return parseFloat(number.toFixed(10)).toString();
        }
        
        return number.toLocaleString();
    }
    
    // 输入数字
    inputNumber(num) {
        if (this.currentInput === 'Error') {
            this.clearAll();
        }
        
        if (this.waitingForOperand) {
            this.currentInput = num;
            this.waitingForOperand = false;
        } else {
            this.currentInput = this.currentInput === '0' ? num : this.currentInput + num;
        }
        
        this.updateDisplay();
    }
    
    // 输入小数点
    inputDecimal() {
        if (this.currentInput === 'Error') {
            this.clearAll();
        }
        
        if (this.waitingForOperand) {
            this.currentInput = '0.';
            this.waitingForOperand = false;
        } else if (this.currentInput.indexOf('.') === -1) {
            this.currentInput += '.';
        }
        
        this.updateDisplay();
    }
    
    // 输入运算符
    inputOperator(nextOperator) {
        const inputValue = parseFloat(this.currentInput);
        
        if (this.currentInput === 'Error') {
            return;
        }
        
        if (this.previousInput === '') {
            this.previousInput = inputValue;
        } else if (this.operator) {
            const currentValue = this.previousInput || 0;
            const newValue = this.performCalculation();
            
            if (newValue === null) return;
            
            this.currentInput = String(newValue);
            this.previousInput = newValue;
        }
        
        this.waitingForOperand = true;
        this.operator = nextOperator;
        this.history = `${this.formatNumber(this.previousInput)} ${this.getOperatorSymbol(nextOperator)}`;
        this.updateDisplay();
    }
    
    // 获取运算符符号
    getOperatorSymbol(op) {
        const symbols = {
            '+': '+',
            '-': '-',
            '*': '×',
            '/': '÷',
            '^': '^'
        };
        return symbols[op] || op;
    }
    
    // 执行计算
    performCalculation() {
        const prev = parseFloat(this.previousInput);
        const current = parseFloat(this.currentInput);
        
        if (isNaN(prev) || isNaN(current)) return null;
        
        let result;
        
        switch (this.operator) {
            case '+':
                result = prev + current;
                break;
            case '-':
                result = prev - current;
                break;
            case '*':
                result = prev * current;
                break;
            case '/':
                if (current === 0) {
                    this.showError('除数不能为零');
                    return null;
                }
                result = prev / current;
                break;
            case '^':
                result = Math.pow(prev, current);
                break;
            default:
                return null;
        }
        
        if (!isFinite(result)) {
            this.showError('结果超出范围');
            return null;
        }
        
        return result;
    }
    
    // 计算结果
    calculate() {
        if (this.currentInput === 'Error' || this.operator === '') {
            return;
        }
        
        const result = this.performCalculation();
        
        if (result !== null) {
            this.history = `${this.formatNumber(this.previousInput)} ${this.getOperatorSymbol(this.operator)} ${this.formatNumber(this.currentInput)} =`;
            this.currentInput = String(result);
            this.previousInput = '';
            this.operator = '';
            this.waitingForOperand = true;
            this.updateDisplay();
        }
    }
    
    // 清除所有
    clearAll() {
        this.currentInput = '0';
        this.previousInput = '';
        this.operator = '';
        this.waitingForOperand = false;
        this.history = '';
        this.display.current.classList.remove('error');
        this.updateDisplay();
    }
    
    // 清除当前输入
    clearEntry() {
        this.currentInput = '0';
        this.updateDisplay();
    }
    
    // 删除最后一位
    deleteLast() {
        if (this.currentInput === 'Error') {
            this.clearAll();
            return;
        }
        
        if (this.currentInput.length > 1) {
            this.currentInput = this.currentInput.slice(0, -1);
        } else {
            this.currentInput = '0';
        }
        
        this.updateDisplay();
    }
    
    // 切换正负号
    toggleSign() {
        if (this.currentInput === 'Error' || this.currentInput === '0') {
            return;
        }
        
        if (this.currentInput.charAt(0) === '-') {
            this.currentInput = this.currentInput.slice(1);
        } else {
            this.currentInput = '-' + this.currentInput;
        }
        
        this.updateDisplay();
    }
    
    // 输入科学函数
    inputFunction(func) {
        const current = parseFloat(this.currentInput);
        
        if (this.currentInput === 'Error' || isNaN(current)) {
            return;
        }
        
        let result;
        
        try {
            switch (func) {
                case 'sin(':
                    result = Math.sin(this.toRadians(current));
                    break;
                case 'cos(':
                    result = Math.cos(this.toRadians(current));
                    break;
                case 'tan(':
                    result = Math.tan(this.toRadians(current));
                    break;
                case 'log(':
                    if (current <= 0) {
                        this.showError('对数的真数必须大于0');
                        return;
                    }
                    result = Math.log10(current);
                    break;
                case 'ln(':
                    if (current <= 0) {
                        this.showError('自然对数的真数必须大于0');
                        return;
                    }
                    result = Math.log(current);
                    break;
                case 'sqrt(':
                    if (current < 0) {
                        this.showError('不能计算负数的平方根');
                        return;
                    }
                    result = Math.sqrt(current);
                    break;
                case 'abs(':
                    result = Math.abs(current);
                    break;
                default:
                    return;
            }
            
            if (!isFinite(result)) {
                this.showError('结果超出范围');
                return;
            }
            
            this.history = `${func.slice(0, -1)}(${this.formatNumber(current)}) =`;
            this.currentInput = String(result);
            this.waitingForOperand = true;
            this.updateDisplay();
            
        } catch (error) {
            this.showError('计算错误');
        }
    }
    
    // 角度转弧度
    toRadians(degrees) {
        return degrees * (Math.PI / 180);
    }
    
    // 显示错误
    showError(message) {
        this.currentInput = 'Error';
        this.history = message;
        this.display.current.classList.add('error');
        this.updateDisplay();
        
        // 3秒后自动清除错误
        setTimeout(() => {
            if (this.currentInput === 'Error') {
                this.clearAll();
            }
        }, 3000);
    }
    
    // 切换科学计算器模式
    toggleScientific() {
        this.scientificMode = !this.scientificMode;
        const scientificButtons = document.getElementById('scientificButtons');
        
        if (this.scientificMode) {
            scientificButtons.style.display = 'grid';
        } else {
            scientificButtons.style.display = 'none';
        }
    }
    
    // 设置键盘事件
    setupKeyboardEvents() {
        document.addEventListener('keydown', (e) => {
            e.preventDefault();
            
            // 数字键
            if (e.key >= '0' && e.key <= '9') {
                this.inputNumber(e.key);
            }
            // 运算符
            else if (e.key === '+') {
                this.inputOperator('+');
            }
            else if (e.key === '-') {
                this.inputOperator('-');
            }
            else if (e.key === '*') {
                this.inputOperator('*');
            }
            else if (e.key === '/') {
                this.inputOperator('/');
            }
            // 小数点
            else if (e.key === '.' || e.key === ',') {
                this.inputDecimal();
            }
            // 等号
            else if (e.key === 'Enter' || e.key === '=') {
                this.calculate();
            }
            // 退格
            else if (e.key === 'Backspace') {
                this.deleteLast();
            }
            // 清除
            else if (e.key === 'Escape' || e.key.toLowerCase() === 'c') {
                this.clearAll();
            }
        });
    }
}

// 创建计算器实例
const calculator = new Calculator();

// 全局函数供HTML调用
function inputNumber(num) {
    calculator.inputNumber(num);
}

function inputOperator(op) {
    calculator.inputOperator(op);
}

function inputDecimal() {
    calculator.inputDecimal();
}

function calculate() {
    calculator.calculate();
}

function clearAll() {
    calculator.clearAll();
}

function clearEntry() {
    calculator.clearEntry();
}

function deleteLast() {
    calculator.deleteLast();
}

function toggleSign() {
    calculator.toggleSign();
}

function inputFunction(func) {
    calculator.inputFunction(func);
}

function toggleScientific() {
    calculator.toggleScientific();
}