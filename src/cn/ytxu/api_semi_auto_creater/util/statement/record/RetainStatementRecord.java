package cn.ytxu.api_semi_auto_creater.util.statement.record;

import cn.ytxu.apacer.entity.RetainEntity;
import cn.ytxu.api_semi_auto_creater.util.statement.Statement;
import cn.ytxu.api_semi_auto_creater.util.statement.StatementRecord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ytxu on 2016/7/18.
 */
public class RetainStatementRecord extends StatementRecord {
    private static final String PATTERN_FRONT = "type=\"";
    private static final String PATTERN_END = "\"";
    private static final Pattern PATTERN = Pattern.compile("(type=\")\\w+(\")");

    public enum RetainType {
        Import("import") {
            @Override
            public StringBuffer getRetainContent(RetainEntity retain) {
                return retain.getImportData();
            }
        },
        Field("field") {
            @Override
            public StringBuffer getRetainContent(RetainEntity retain) {
                return retain.getFieldData();
            }
        },
        Method("method") {
            @Override
            public StringBuffer getRetainContent(RetainEntity retain) {
                return retain.getMethodData();
            }
        },
        Other("other") {
            @Override
            public StringBuffer getRetainContent(RetainEntity retain) {
                return retain.getOtherData();
            }
        };

        private final String name;

        RetainType(String name) {
            this.name = name;
        }

        public abstract StringBuffer getRetainContent(RetainEntity retain);

        public static RetainType get(String retainTypeName) {
            for (RetainType type : RetainType.values()) {
                if (type.name.equals(retainTypeName)) {
                    return type;
                }
            }
            throw new IllegalStateException("i do not defind this retain type, the error type name is " + retainTypeName);
        }
    }

    private RetainType type;

    public RetainStatementRecord(Statement statement, String startTagContent) {
        super(statement, startTagContent, null);
    }


    @Override
    public void parse() {
        String retainTypeName = getRetainTypeName();
        type = RetainType.get(retainTypeName);
    }

    private String getRetainTypeName() {
        Matcher matcher = PATTERN.matcher(startTagContent);
        // be sure to match, but also need call matcher.find()
        matcher.find();
        String group = matcher.group();
        int methodNameStart = PATTERN_FRONT.length();
        int methodNameEnd = group.length() - PATTERN_END.length();
        return group.substring(methodNameStart, methodNameEnd);
    }

    @Override
    public StringBuffer getWriteBuffer(Object reflectModel, RetainEntity retain) {
        return type.getRetainContent(retain);
    }

}